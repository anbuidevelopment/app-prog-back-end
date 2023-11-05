package com.apps.prog.appprosbackend.api.features.lms.task_comment

import com.apps.prog.appprosbackend.api.exception.AppException
import com.apps.prog.appprosbackend.api.features.lms.task_comment.entity.TaskComment
import com.apps.prog.appprosbackend.api.features.lms.task_comment.entity.TaskCommentHistory
import com.apps.prog.appprosbackend.api.features.lms.task_comment.model.TaskCommentRequest
import com.apps.prog.appprosbackend.api.features.lms.task_comment.model.TaskCommentResponse
import com.apps.prog.appprosbackend.utils.formatToString
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TaskCommentService(
    private val taskCommentRepository: TaskCommentRepository,
    private val taskHistory: TaskCommentHistoryRepository
) {
    fun findAllCommentByTaskId(taskId: Long): List<TaskCommentResponse> {
        return taskCommentRepository.getTaskCommentResponses(taskId)
    }

    @Transactional
    fun save(rq: TaskCommentRequest): TaskComment {
        val comment = if (rq.taskCommentId != null) {
            updateComment(rq)
        } else {
            createComment(rq)
        }

        return taskCommentRepository.save(comment)
    }

    private fun createComment(rq: TaskCommentRequest): TaskComment {
        return TaskComment(
            taskId = rq.taskId,
            accountId = rq.accountId,
            content = rq.content,
            parentTaskCommentId = rq.parentTaskCommentId,
            createdBy = rq.accountId,
            createdDate = LocalDateTime.now().formatToString(),
            updatedBy = rq.accountId,
            updatedDate = LocalDateTime.now().formatToString(),
        )
    }

    private fun updateComment(rq: TaskCommentRequest): TaskComment {
        val existingComment = taskCommentRepository.findById(rq.taskCommentId!!)
            .orElseThrow { AppException("Comment with id ${rq.taskCommentId} not found") }

        taskHistory.save(
            TaskCommentHistory(
                taskCommentId = existingComment.taskCommentId!!,
                accountId = existingComment.accountId,
                content = existingComment.content
            )
        )

        return existingComment.copy(
            content = rq.content,
            parentTaskCommentId = rq.parentTaskCommentId,
            updatedDate = LocalDateTime.now().formatToString()
        )

    }
}