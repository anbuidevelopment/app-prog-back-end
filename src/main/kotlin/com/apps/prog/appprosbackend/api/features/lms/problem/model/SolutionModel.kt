package com.apps.prog.appprosbackend.api.features.lms.problem.model

import com.apps.prog.appprosbackend.utils.formatToString
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "Solution")
@Table(name = "Solution", schema = "prb")
data class SolutionModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SolutionId")
    val solutionId: Long? = null,

    @Column(name = "DepartmentType", nullable = false)
    val departmentType: String,

    @Column(name = "SolutionCode", nullable = false, unique = true, length = 30)
    val solutionCode: String,

    @Column(name = "SolutionDescription", nullable = false, columnDefinition = "nvarchar(500) default getdate()")
    val solutionDescription: String = "",

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val createdDate: String = LocalDateTime.now().formatToString(),

    @Column(name = "UpdatedBy", nullable = false)
    val updatedBy: Int,

    @Column(name = "UpdatedDate", nullable = false, columnDefinition = "datetime default getdate()")
    val updatedDate: String = LocalDateTime.now().formatToString(),

    @Column(name = "SolutionType", nullable = false, length = 30)
    val solutionType: String
)
