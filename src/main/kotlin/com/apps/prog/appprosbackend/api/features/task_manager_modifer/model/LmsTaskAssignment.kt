package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model

import com.apps.prog.appprosbackend.api.features.authentication.model.LmsUser
import jakarta.persistence.*

@Entity
@Table(name = "lms_task_assignment")
data class LmsTaskAssignment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_assignment_id")
    val taskAssignmentId: Long = 0,

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    val task: LmsTask,

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id", nullable = false)
    val assignedToUser: LmsUser,

    @ManyToOne
    @JoinColumn(name = "assigned_by_user_id", nullable = false)
    val assignedByUser: LmsUser,

    @Column(nullable = false)
    val status: Int = 0,
)