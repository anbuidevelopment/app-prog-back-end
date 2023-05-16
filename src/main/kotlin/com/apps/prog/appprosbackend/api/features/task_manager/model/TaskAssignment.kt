package com.apps.prog.appprosbackend.api.features.task_manager.model

import com.apps.prog.appprosbackend.api.features.authentication.User
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

@Entity(name = "lms_task_assignment")
@Table(name = "lms_task_assignment")
data class TaskAssignment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_assignment_id")
    val taskAssignmentId: Int = 0,

    @Column(name = "status")
    @JsonIgnore
    var status: Status = Status.NEW,

    @Column(name = "date_complete")
    @JsonIgnore
    var dateComplete: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "task_id")
    var task: Task? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    var user: User? = null,
)

data class TaskAssignmentId(
    var taskId: Long = 0,
    var userId: Int = 0
) : Serializable