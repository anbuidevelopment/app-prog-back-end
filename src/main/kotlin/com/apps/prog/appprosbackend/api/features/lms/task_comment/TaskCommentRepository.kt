package com.apps.prog.appprosbackend.api.features.lms.task_comment

import com.apps.prog.appprosbackend.api.features.lms.task_comment.entity.TaskComment
import com.apps.prog.appprosbackend.api.features.lms.task_comment.model.TaskCommentResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TaskCommentRepository : JpaRepository<TaskComment, Long> {
    @Query("SELECT NEW com.apps.prog.appprosbackend.api.features.lms.task_comment.model.TaskCommentResponse(tc.taskCommentId, tc.taskId, tc.accountId, tc.parentTaskCommentId, tc.content, acc.accountName, acc.accountCode, tc.createdBy, tc.createdDate, tc.updatedDate) FROM TaskComments tc INNER JOIN Account acc ON tc.accountId = acc.accountId WHERE tc.taskId = :taskId")
    fun getTaskCommentResponses(@Param("taskId") taskId: Long): List<TaskCommentResponse>

}