package com.apps.prog.appprosbackend.api.features.lms.task_comment.model

data class TaskCommentResponse(
    val taskCommentId: Long,
    val taskId: Long,
    val accountId: Int,
    val parentTaskCommentId: Long?,
    val content: String,
    val accountName: String,
    val accountCode: String,
    val createdBy: Int,
    val createdDate: String,
    val updatedDate: String,
)
