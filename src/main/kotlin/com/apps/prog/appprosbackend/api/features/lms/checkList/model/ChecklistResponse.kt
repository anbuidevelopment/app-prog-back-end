package com.apps.prog.appprosbackend.api.features.lms.checkList.model

import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItemCompletion
import java.time.LocalDate
import java.time.LocalDateTime

data class ChecklistResponse(
    val checklistDesc: String,
    val checklistId: Long,
    val checklistItemId: Long,
    val itemTitle: String,
    val itemDescription: String,
    val completionDate: Any?
)

