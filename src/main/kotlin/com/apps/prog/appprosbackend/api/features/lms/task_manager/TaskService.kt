package com.apps.prog.appprosbackend.api.features.lms.task_manager

import com.apps.prog.appprosbackend.api.exception.AppException
import com.apps.prog.appprosbackend.api.features.authentication.AccountRepository
import com.apps.prog.appprosbackend.api.features.authentication.AccountService
import com.apps.prog.appprosbackend.api.features.lms.fcm.FCMService
import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.Task
import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.TaskAssignment
import com.apps.prog.appprosbackend.api.features.lms.task_manager.model.TaskRequest
import com.apps.prog.appprosbackend.api.features.lms.task_manager.model.TaskResponse
import com.apps.prog.appprosbackend.utils.ErrorConstants
import com.apps.prog.appprosbackend.utils.MsgTaskConstant
import com.apps.prog.appprosbackend.utils.formatToString
import com.apps.prog.appprosbackend.utils.getMsg
import org.hibernate.query.sqm.tree.SqmNode.log
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.function.Consumer

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val taskAssignRepository: TaskAssignRepository,
    private val fcmService: FCMService,
    private val accountRepository: AccountRepository,
    private val messageSource: MessageSource
) {
    private val logger = LoggerFactory.getLogger(TaskService::class.java)
    val locale = LocaleContextHolder.getLocale()

    fun findTasksByAccountId(accountId: Int): List<TaskResponse> {
        val tasksAndAssignments = taskRepository.findTasksAndAssignmentsByAccountId(accountId)
        var listAccountId = listOf<Int>()
        val taskResponses = tasksAndAssignments.groupBy(
            { it.getTask() },
            { it.getTaskAssignment().assignedToAccountId },
        ).map { (task, assignedToAccountIds) ->
            listAccountId = assignedToAccountIds

            if (assignedToAccountIds.size < 2) {
                val accountIds = taskAssignRepository.findAssignedAccountIdsByTaskId(taskId = task.taskId!!)
                listAccountId = accountIds
            }

            TaskResponse(
                taskId = task.taskId!!,
                title = task.title,
                desc = task.description,
                dueDate = task.dueDate,
                deadlineTime = task.deadlineTime,
                status = task.status,
                priority = task.priority,
                assignedByAccountId = task.createdBy,
                createdBy = task.createdBy,
                createdDate = task.createdDate,
                updatedBy = task.updatedBy,
                updatedDate = task.updatedDate,
                assignedToAccountIds = listAccountId
            )
        }

        return taskResponses
    }

    @Transactional
    fun deleteTaskAssignmentsByTaskId(taskId: Long) {
        taskAssignRepository.deleteByTaskId(taskId)
    }
//    fun save(rq: TaskRequest) {
//        try {
//            val taskResult = taskRepository.save(
//                Task(
//                    title = rq.title,
//                    description = rq.desc,
//                    dueDate = rq.dueDate,
//                    deadlineTime = rq.deadlineTime,
//                    status = rq.status,
//                    priority = rq.priority,
//                    createdBy = rq.assignByAccountId,
//                    createdDate = LocalDateTime.now().formatToString(),
//                    updatedBy = rq.assignByAccountId,
//                    updatedDate = LocalDateTime.now().formatToString()
//                )
//            )
//
//            val taskAssignments = rq.assignToAccountId.map { accountId ->
//                TaskAssignment(
//                    taskId = taskResult.taskId,
//                    assignedByAccountId = rq.assignByAccountId,
//                    assignedToAccountId = accountId
//                )
//            }
//
//            taskAssignRepository.saveAll(taskAssignments)
//        } catch (e: Exception) {
//            throw AppException(message = messageSource.getMsg(localizeMsg = ErrorConstants.ERROR_CREATE_TASK))
//        }
//    }


    @Transactional
    fun save(rq: TaskRequest): TaskResponse? {
        try {
            val task = if (rq.taskId != null) {
                updateExistingTask(rq)
            } else {
                createNewTask(rq)
            }

            val taskResult = taskRepository.save(task)

            if (rq.taskId != null) {
                deleteTaskAssignmentsByTaskId(rq.taskId)
            }

            sendNotificationsAsync(rq, taskResult)

            val taskAssignments = createTaskAssignments(rq.assignToAccountId, rq.assignByAccountId, taskResult.taskId!!)
            taskAssignRepository.saveAll(taskAssignments)

            return taskResult.toResponse()

        } catch (e: Exception) {
            e.printStackTrace()
//            log.error("Error occurred while saving task: ${e.message}", e)
            throw AppException(message = messageSource.getMsg(localizeMsg = ErrorConstants.ERROR_CREATE_TASK))
        }
    }

    private fun updateExistingTask(rq: TaskRequest): Task {
        val existingTask = taskRepository.findById(rq.taskId!!)
            .orElseThrow { AppException("Task with id ${rq.taskId} not found") }

        return existingTask.copy(
            title = rq.title,
            description = rq.desc,
            dueDate = rq.dueDate,
            deadlineTime = rq.deadlineTime,
            status = rq.status,
            priority = rq.priority,
            // Update other fields as needed
            updatedBy = rq.assignByAccountId,
            updatedDate = LocalDateTime.now().formatToString()
        )
    }

    private fun createNewTask(rq: TaskRequest): Task {
        return Task(
            title = rq.title,
            description = rq.desc,
            dueDate = rq.dueDate,
            deadlineTime = rq.deadlineTime,
            status = rq.status,
            priority = rq.priority,
            createdBy = rq.assignByAccountId,
            createdDate = LocalDateTime.now().formatToString(),
            updatedBy = rq.assignByAccountId,
            updatedDate = LocalDateTime.now().formatToString()
        )
    }

    fun sendNotificationsAsync(rq: TaskRequest, taskResult: Task) {
        rq.assignToAccountId.forEach { accountId ->
            val account = accountRepository.findById(accountId).orElse(null)
            account?.let {
                fcmService.sendNotification(
                    it.fcmToken.toString(), account.accountCode, rq.title + "\n" + rq.desc
                )
            }
        }
    }

    private fun createTaskAssignments(assignToAccountIds: List<Int>, assignByAccountId: Int, taskId: Long): List<TaskAssignment> {
        return assignToAccountIds.map { accountId ->
            TaskAssignment(
                taskId = taskId,
                assignedByAccountId = assignByAccountId,
                assignedToAccountId = accountId.toInt()
            )
        }
    }

    private fun Task.toResponse(): TaskResponse {
        return TaskResponse(
            taskId = this.taskId ?: 0L,
            title = this.title,
            desc = this.description,
            dueDate = this.dueDate,
            deadlineTime = this.deadlineTime,
            status = this.status,
            priority = this.priority,
            assignedByAccountId = this.createdBy, // Or should it be updatedBy?
            assignedToAccountIds = emptyList(), // Assign this after task assignments are saved
            createdBy = this.createdBy,
            createdDate = this.createdDate,
            updatedBy = this.updatedBy,
            updatedDate = this.updatedDate
        )
    }


}