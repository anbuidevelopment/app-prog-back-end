package com.apps.prog.appprosbackend.api.features.lms.checkList.entity

import jakarta.persistence.*

@Entity
@Table(name = "Checklists", schema = "lms")
data class Checklist(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChecklistId")
    val checklistId: Int,

    @Column(name = "Description", nullable = false)
    val description: String,

    @Column(name = "Type", nullable = false)
    val type: Short,

    @Column(name = "AssignedToAccountId")
    var assignedToAccountId: Int? = null,
)
