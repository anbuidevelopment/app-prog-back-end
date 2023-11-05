package com.apps.prog.appprosbackend.api.features.lms.fcm

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "EscalationPolicyData")
@Table(name = "EscalationPolicyData", schema = "lms")
data class FCMDataEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val escalationPolicyDataId: Long = 0,

    @Column(name = "AppId", nullable = false)
    val appId: Int,

    @Column(name = "FactoryCode", nullable = false)
    val factoryCode: String,

    @Column(name = "DepartmentCode", nullable = false)
    val departmentCode: String,

    @Column(name = "Subject", nullable = false)
    val subject: String,

    @Column(name = "Content", nullable = false)
    val content: String,

    @Column(name = "StaffCode", nullable = false)
    val staffCode: String,

    @Column(name = "EscalationPolicyId", nullable = false)
    val escalationPolicyId: Int,

    @Column(name = "Repeat", nullable = false)
    val repeat: Int,

    @Column(name = "RepeatTime", nullable = false)
    val repeatTime: Int,

    @Column(name = "Priority", nullable = false)
    val priority: Int,

    @Column(name = "Status", nullable = false)
    val status: Short,

    @Column(name = "IsRead", nullable = false)
    val isRead: Boolean,

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", nullable = false)
    val createdDate: String,

    @Column(name = "UpdatedBy", nullable = false)
    val updatedBy: Int,

    @Column(name = "UpdatedDate", nullable = false)
    val updatedDate: String,

    @Column(name = "HasSolution")
    val hasSolution: Boolean,
)