package com.apps.prog.appprosbackend.api.features.task_manager.model

import com.apps.prog.appprosbackend.api.features.authentication.User

data class AssignTaskRequest(
    val task: Task,
    val user: User
)
