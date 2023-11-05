package com.apps.prog.appprosbackend.api.features.lms.task_comment.model

data class TaskCommentRequest(
    val taskCommentId: Long? = null,
    val taskId: Long,
    val accountId: Int,
    val content: String,
    val parentTaskCommentId: Long? = null,
    val createdBy: Int,
    val createdDate: String,
    val updatedBy: Int,
    val updatedDate: String
)
