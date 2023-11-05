package com.apps.prog.appprosbackend.api.features.lms.problem.model.request

import com.apps.prog.appprosbackend.utils.formatToString
import java.time.LocalDateTime

data class ProblemRequest(
    val departmentType: String,
    val departmentCode: String,
    val type: String,
    val createdBy: Int,
    val reasonId: Long = 0,
    val reasonCode: String,
    val reasonDesc: String,
    val solutionId: Long = 0,
    val solutionCode: String,
    val solutionDesc: String,
    val problemTicketTitle: String,
    val problemTicketCode: String,
    val problemTicketContent: String,
    val problemTicketStatus: Short = 10,
    val solvedDuration: Double = 10.5,
    val esId: Int = 0,
    val startTime: String = "",
    val createdDate: String = LocalDateTime.now().formatToString(),
    val updatedDate: String = LocalDateTime.now().formatToString()
)
