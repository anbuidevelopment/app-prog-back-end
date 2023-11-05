package com.apps.prog.appprosbackend.api.features.lms.task_manager.entity

import com.apps.prog.appprosbackend.api.features.authentication.entity.Account
import jakarta.persistence.*


@Entity(name = "TaskAssignments")
@Table(name = "TaskAssignments", schema = "lms")
data class TaskAssignment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskAssignmentId")
    val taskAssignmentId: Long? = null,

    @Column(name = "TaskId")
    val taskId: Long? = null,

    @Column(name = "AssignedToAccountId")
    val assignedToAccountId: Int,

    @Column(name = "AssignedByAccountId")
    val assignedByAccountId: Int
)
