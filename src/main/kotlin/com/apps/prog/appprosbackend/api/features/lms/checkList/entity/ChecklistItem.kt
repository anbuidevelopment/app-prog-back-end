package com.apps.prog.appprosbackend.api.features.lms.checkList.entity

import jakarta.persistence.*

@Entity
@Table(name = "ChecklistItems", schema = "lms")
data class ChecklistItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChecklistItemId")
    val checklistItemId: Int,

    @Column(name = "ChecklistId")
    val checklistId: Int,

    @Column(name = "Title")
    val title: String?,

    @Column(name = "Description", nullable = false)
    val description: String,

    @Column(name = "Priority")
    val priority: Int
)
