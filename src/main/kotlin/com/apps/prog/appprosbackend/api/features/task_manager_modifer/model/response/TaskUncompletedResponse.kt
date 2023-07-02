package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.response


data class TaskUncompletedResponse(
    val task: TaskResponse,
    val dueDate: String,
    val startTime: String?,
    val endTime: String?,
    val isCompleted: Boolean = false,
//    val assignedByUserId: Int
//    val assignedToUserId: Int
)