package com.apps.prog.appprosbackend.api.features.lms.problem.model

import com.apps.prog.appprosbackend.utils.formatToString
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "ProblemTicket")
@Table(name = "ProblemTicket", schema = "prb")
data class ProblemTicketModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProblemTicketId")
    val problemTicketId: Long? = null,

    @Column(name = "DepartmentCode", nullable = false, length = 10)
    val departmentCode: String,

    @Column(name = "ProblemTicketCode", nullable = false, unique = true, length = 30)
    val problemTicketCode: String,

    @Column(name = "ProblemTicketTitle", nullable = false, length = 200)
    val problemTicketTitle: String,

    @Column(name = "ProblemTicketContent", columnDefinition = "nvarchar(500)")
    val problemTicketContent: String? = null,

    @Column(name = "ProblemTicketStatus", nullable = false)
    val problemTicketStatus: Short,

    @Column(name = "StartTime", nullable = false)
    val startTime: String,

    @Column(name = "EndTime")
    val endTime: LocalDateTime? = null,

    @Column(name = "SolvedDuration", columnDefinition = "decimal(19, 2) default 0", nullable = false)
    val solvedDuration: BigDecimal = BigDecimal.ZERO,

    @Column(name = "Note", columnDefinition = "nvarchar(200)")
    val note: String? = null,

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val createdDate: String = LocalDateTime.now().formatToString(),

    @Column(name = "UpdatedBy", nullable = false)
    val updatedBy: Int,

    @Column(name = "UpdatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val updatedDate: String = LocalDateTime.now().formatToString()
)

