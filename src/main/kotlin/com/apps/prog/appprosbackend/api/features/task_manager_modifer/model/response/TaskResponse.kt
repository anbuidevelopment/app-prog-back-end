package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.response

data class TaskResponse(
    val taskId: Int,
    val name: String,
    val description: String?,
    val note: String?,
    val status: Int,
    val priority: Int,
    val type: Int,
    val dueDate: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val isCompleted: Boolean = false,
)


data class TaskResponseDepartment(
    val taskId: Int,
    val name: String,
    val description: String?,
    val note: String?,
    val status: Int,
    val priority: Int,
    val type: Int,
    val dueDate: String? = null,
    val assignedToUserId: Int,
)
