package com.apps.prog.appprosbackend.api.features.lms.problem.model

import com.apps.prog.appprosbackend.utils.formatToString
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "ProblemTicketSolution")
@Table(name = "ProblemTicketSolution", schema = "prb")
data class ProblemTicketSolutionModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProblemTicketId")
    val problemTicketId: Long? = null,

    @Column(name = "SolutionId", nullable = false)
    val solutionId: Long,

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val createdDate: String = LocalDateTime.now().formatToString(),
)