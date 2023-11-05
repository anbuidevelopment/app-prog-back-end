package com.apps.prog.appprosbackend.api.features.lms.checkList.model

data class ChecklistRequest(
    val type: Int = 0,
    val day: Int = 0,
    val weekOfMonth: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
)
