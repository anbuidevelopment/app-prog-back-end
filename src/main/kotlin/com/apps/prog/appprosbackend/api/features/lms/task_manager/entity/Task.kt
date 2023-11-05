package com.apps.prog.appprosbackend.api.features.lms.task_manager.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalDateTime

@Entity(name = "Tasks")
@Table(name = "Tasks", schema = "lms")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskId")
    val taskId: Long? = null,

    @Column(name = "Title", nullable = false)
    val title: String,

    @Column(name = "Description")
    val description: String? = null,

    @Column(name = "DueDate", nullable = false)
    val dueDate: String? = null,

    @Column(name = "DeadlineTime")
    val deadlineTime: String? = null,

    @Column(name = "Status")
    val status: Short? = null,

    @Column(name = "Priority")
    val priority: Short,

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", insertable = false, updatable = false)
    val createdDate: String,

    @Column(name = "UpdatedBy", nullable = false)
    val updatedBy: Int,

    @Column(name = "UpdatedDate", insertable = false, updatable = false)
    val updatedDate: String
)
