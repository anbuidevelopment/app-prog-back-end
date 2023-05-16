package com.apps.prog.appprosbackend.api.features.task_manager.model

import jakarta.persistence.*

@Entity
@Table(name = "lms_type")
data class TaskType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    val id: Long? = null,

    @Column(name = "type_name", nullable = false)
    val name: String
)
