package com.apps.prog.appprosbackend.api.features.task_manager_modifer.service

import com.apps.prog.appprosbackend.api.common.ApiErrorResponse
import com.apps.prog.appprosbackend.api.features.authentication.UserRepository
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.Constants
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.*
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.request.TaskRequest
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.response.TaskResponse
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.response.TaskResponseDepartment
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository.*
import com.apps.prog.appprosbackend.utils.ErrorConstants
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val taskType: TaskTypeRepository,
    private val userRepository: UserRepository,
    private val taskAssignmentRepository: TaskAssignmentRepository,
    private val normalCheckInRepository: NormalCheckInRepository,
    private val dailyCheckInRepository: DailyCheckInRepository,
    private val weeklyCheckInRepository: WeeklyCheckInRepository,
    private val monthlyCheckInRepository: MonthlyCheckInRepository,
    private val messageSource: MessageSource
) {
    @Autowired
    lateinit var entityManager: EntityManager

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @Transactional
    fun updateTaskStatus(status: Int, taskId: Long): ResponseEntity<Any> {
        return try {
            val query = entityManager.createNativeQuery("UPDATE lms_task SET status = :status WHERE task_id = :taskId")
            query.setParameter("status", status)
            query.setParameter("taskId", taskId)
            val rowUpdated = query.executeUpdate()

            ResponseEntity.ok(rowUpdated)
        } catch (e: Exception) {
            e.printStackTrace()
            print(e.localizedMessage)
            val response = ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Lỗi không xác định")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }
    }

    fun getTaskOfUsersInDepartment(department: String): ResponseEntity<Any> {
        return try {
            val query = entityManager.createNativeQuery(
                "SELECT DISTINCT t.task_id, t.task_name, t.task_description, t.type_id, t.priority, t.note, t.status, nm.due_date ,u.user_id AS assigned_to_user_id\n" +
                        "FROM lms_task_assignment ta\n" +
                        "JOIN lms_task t ON ta.task_id = t.task_id\n" +
                        "JOIN lms_users u ON ta.assigned_to_user_id = u.user_id\n" +
                        "JOIN lms_normal_check_ins nm ON ta.task_id = nm.task_id\n" +
                        "WHERE u.department = :department"
            )

            query.setParameter("department", department)
            ResponseEntity.ok(responseAllTaskInDepartment(query.resultList))
        } catch (e: Exception) {
            e.printStackTrace()
            print(e.localizedMessage)
            val response = ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Lỗi không xác định")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }

    }

    fun createTask(payload: TaskRequest): ResponseEntity<Any> {
        return try {
            val task = LmsTask(
                taskName = payload.taskName,
                taskDescription = payload.taskDescription,
                type = taskType.findById(payload.typeId).orElseThrow(),
                priority = payload.priority,
                status = payload.status,
                note = payload.note
            )

            val createdTask = taskRepository.save(task)

            payload.assignRequest.assignedToUsers.forEach { assignedUserId ->
                val assignedTask = LmsTaskAssignment(
                    task = createdTask,
                    assignedToUser = userRepository.findById(assignedUserId).orElseThrow(),
                    assignedByUser = userRepository.findById(payload.assignRequest.assignedByUser).orElseThrow()
                )

                taskAssignmentRepository.save(assignedTask)

                when (payload.typeId) {
                    1 -> createNormalTask(payload, createdTask.taskId, assignedUserId)
                    2 -> createDailyTask(payload, createdTask.taskId, assignedUserId)
                    3 -> createWeeklyTask(payload, createdTask.taskId, assignedUserId)
                    4 -> createMonthlyTask(payload, createdTask.taskId, assignedUserId)
                }
            }
            ResponseEntity.ok(createdTask)
        } catch (e: Exception) {
            val response = ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.localizedMessage)
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response)
        }
    }

    fun getAllTasksByUser(userId: Int): ResponseEntity<Any> {
        val locale = LocaleContextHolder.getLocale()
        return try {
            val query = entityManager.createNativeQuery(Constants.queryAllTasks)
            query.setParameter(1, userId)
            ResponseEntity.ok(response(query.resultList))
        } catch (e: NullPointerException) {
            val msgError = messageSource.getMessage(ErrorConstants.ERROR_USER_NOT_FOUND, arrayOf(userId), locale)
            val response = ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), msgError)
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response)
        }
    }

    fun getAllTasksByUserAndDate(userId: Int, dueDate: String): ResponseEntity<Any> {
        val query = entityManager.createNativeQuery(Constants.queryTasksByUserAndDate).apply {
            setParameter(1, userId)
            setParameter(2, dueDate)
        }

        val taskResponse = response(query.resultList)
        return ResponseEntity.ok(taskResponse)
    }

    private fun responseAllTaskInDepartment(tasks: List<*>): List<TaskResponseDepartment> {
        return tasks.map { values ->
            val row = values as Array<*>
            val taskId = row[0] as Int
            val dueDate = row[7] as String
            val task = taskRepository.findById(taskId.toLong()).orElse(null)

            TaskResponseDepartment(
                taskId = taskId,
                name = task.taskName,
                description = task.taskDescription,
                note = task.note,
                status = task.status,
                priority = task.priority,
                type = task.type.typeId,
                dueDate = dueDate,
                assignedToUserId = row[8] as Int
            )
        }
    }

    private fun response(lists: List<*>): List<TaskResponse> {
        return lists.map { values ->
            val row = values as Array<*>
            val taskId = row[0] as Int
            val dueDate = row[1] as String
            val startTime = row[2] as String?
            val endTime = row[3] as String?
            val task = taskRepository.findById(taskId.toLong()).orElse(null)

            TaskResponse(
                taskId = taskId,
                name = task.taskName,
                description = task.taskDescription,
                note = task.note,
                status = task.status,
                priority = task.priority,
                type = task.type.typeId,
                dueDate = dueDate,
                startTime = startTime,
                endTime = endTime,
                isCompleted = row[4] as Boolean
            )
        }
    }

    private fun createNormalTask(taskRequest: TaskRequest, taskId: Int, assignedToUser: Int) {
        val normalCheckIn = LmsNormalCheckIn(
            taskId = taskId,
            user = userRepository.findById(assignedToUser).orElseThrow(),
            dueDate = taskRequest.dueDate ?: formatter.format(LocalDate.now()),
            startTime = taskRequest.startTime,
            endTime = taskRequest.endTime
        )
        normalCheckInRepository.save(normalCheckIn)
    }

    private fun createDailyTask(task: TaskRequest, taskId: Int, assignedToUser: Int) {
        val dailyCheckIn = LmsDailyCheckIn(
            taskId = taskId,
            user = userRepository.findById(assignedToUser).orElseThrow(),
            checkInDate = formatter.format(LocalDate.now()),
            startTime = task.startTime,
            endTime = task.endTime
        )
        dailyCheckInRepository.save(dailyCheckIn)
    }

    private fun createWeeklyTask(task: TaskRequest, taskId: Int, assignedToUser: Int) {
        val weeklyCheckIn = LmsWeeklyCheckIn(
            taskId = taskId,
            user = userRepository.findById(assignedToUser).orElseThrow(),
            checkInWeek = formatter.format(LocalDate.now()),
            startTime = task.startTime,
            endTime = task.endTime
        )
        weeklyCheckInRepository.save(weeklyCheckIn)
    }

    private fun createMonthlyTask(task: TaskRequest, taskId: Int, assignedToUser: Int) {
        val monthlyCheckIn = LmsMonthlyCheckIn(
            taskId = taskId,
            user = userRepository.findById(assignedToUser).orElseThrow(),
            checkInMonth = formatter.format(LocalDate.now()),
            startTime = task.startTime,
            endTime = task.endTime
        )
        monthlyCheckInRepository.save(monthlyCheckIn)
    }
}
