package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model

import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.response.TaskResponse
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "lms_task")
data class LmsTask(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    val taskId: Int = 0,

    @Column(name = "task_name", nullable = false)
    val taskName: String,

    @Column(name = "task_description", length = 255)
    val taskDescription: String? = null,

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    val type: LmsType,

    @Column(nullable = false)
    val priority: Int = 0,

    @Column(nullable = false)
    val status: Int = 0,

    @Column(length = 255)
    val note: String? = null,

)