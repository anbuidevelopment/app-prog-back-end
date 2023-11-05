package com.apps.prog.appprosbackend.api.features.lms.task_manager.model

import jakarta.persistence.Column
import java.time.LocalDate

data class TaskRequest(
    val taskId: Long? = null,
    val title: String,
    val desc: String,
    val dueDate: String? = null,
    val deadlineTime: String? = null,
    val status: Short? = null,
    val priority: Short,
    val assignToAccountId: List<Int> = emptyList(),
    val assignByAccountId: Int = 0,
)
