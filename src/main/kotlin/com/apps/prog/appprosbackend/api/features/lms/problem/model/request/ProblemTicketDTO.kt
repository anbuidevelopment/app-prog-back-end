package com.apps.prog.appprosbackend.api.features.lms.problem.model.request

import java.time.LocalDateTime

data class ProblemTicketDTO(
    val problemTicketId: Long,
    val problemTicketCode: String,
    val departmentCode: String,
    val problemTicketTitle: String,
    val problemTicketContent: String,
    val problemTicketStatus: String,
    val createDate: String,
    val startTime: String,
    val endTime: String?,
    val reason: String,
    val solution: String
)
