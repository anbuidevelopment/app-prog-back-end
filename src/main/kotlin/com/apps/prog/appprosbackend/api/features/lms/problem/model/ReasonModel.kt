package com.apps.prog.appprosbackend.api.features.lms.problem.model

import com.apps.prog.appprosbackend.utils.formatToString
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "Reason")
@Table(name = "Reason", schema = "prb")
data class ReasonModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReasonId")
    val reasonId: Long? = null,

    @Column(name = "DepartmentType", nullable = false)
    val departmentType: String,

    @Column(name = "ReasonCode", nullable = false, unique = true, length = 30)
    val reasonCode: String,

    @Column(name = "ReasonDescription", nullable = false, columnDefinition = "nvarchar(500) default getdate()")
    val reasonDescription: String = "",

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val createdDate: String = LocalDateTime.now().formatToString(),

    @Column(name = "UpdatedBy", nullable = false)
    val updatedBy: Int,

    @Column(name = "UpdatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val updatedDate: String = LocalDateTime.now().formatToString(),

    @Column(name = "ReasonType", nullable = false, length = 30)
    val reasonType: String
)

