package com.apps.prog.appprosbackend.api.features.task_manager.model

import java.time.LocalDate
import java.time.LocalDateTime

data class TaskDto(
    val id: Long? = null,

    val title: String,

    val description: String,

    val dueDate: LocalDate,

    val timeRange: String? = null,

    val type: String,

    val priority: Int = 0,

    val startTime: LocalDateTime? = null,

    val endTime: LocalDateTime? = null,

    val status: Status = Status.NEW,

    val note: String? = null
)
