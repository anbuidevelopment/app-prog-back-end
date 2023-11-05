package com.apps.prog.appprosbackend.api.features.lms.task_manager.model

data class TaskResponse(
    val taskId: Long,
    val title: String,
    val desc: String? = null,
    val dueDate: String? = null,
    val deadlineTime: String? = null,
    val status: Short? = null,
    val priority: Short,
    val assignedByAccountId: Int,
    val assignedToAccountIds: List<Int>,
    val createdBy: Int,
    val createdDate: String,
    val updatedBy: Int,
    val updatedDate: String,
)
