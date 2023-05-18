package com.apps.prog.appprosbackend.api.features.task_manager.model.request

import com.apps.prog.appprosbackend.api.features.authentication.User
import com.apps.prog.appprosbackend.api.features.task_manager.model.Task

data class AssignTaskRequest(
    val task: Task,
    val user: User
)
