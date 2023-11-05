package com.apps.prog.appprosbackend.api.features.lms.problem.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "ProblemTicketReason")
@Table(name = "ProblemTicketReason", schema = "prb")
data class ProblemTicketReasonModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProblemTicketId")
    val problemTicketId: Long? = null,

    @Column(name = "ReasonId", nullable = false)
    val reasonId: Long,

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val createdDate: LocalDateTime = LocalDateTime.now(),
)
