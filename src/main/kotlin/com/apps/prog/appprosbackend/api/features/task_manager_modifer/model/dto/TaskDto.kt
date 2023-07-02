package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.dto

import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsDailyCheckIn
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsNormalCheckIn
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.response.TaskResponse

data class TaskDto(
    val task: TaskResponse,
    val assignedToUserId: Int,
    val assignedByUserId: Int,
)

