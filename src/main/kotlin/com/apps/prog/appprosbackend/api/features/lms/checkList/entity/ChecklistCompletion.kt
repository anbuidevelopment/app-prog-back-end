package com.apps.prog.appprosbackend.api.features.lms.checkList.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "ChecklistCompletions")
data class ChecklistCompletion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChecklistCompletionId")
    val checklistCompletionId: Long,

    @Column(name = "ChecklistId")
    val checklistId: Long,

    @Column(name = "AccountId")
    val accountId: Int,

    @Column(name = "CompletionDate")
    val completionDate: LocalDateTime = LocalDateTime.now()
)