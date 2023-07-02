package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model

import jakarta.persistence.*

@Entity
@Table(name = "lms_type")
data class LmsType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    val typeId: Int = 0,

    @Column(name = "type_name", nullable = false)
    val typeName: String
)
