package com.apps.prog.appprosbackend.api.features.lms.checkList.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "ChecklistItemCompletions", schema = "lms")
data class ChecklistItemCompletion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChecklistItemCompletionId")
    val checklistItemCompletionId: Long? = null,

    @Column(name = "ChecklistItemId")
    val checklistItemId: Long,

    @Column(name = "AccountId")
    val accountId: Int,

    @Column(name = "CompletionDate")
    val completionDate: LocalDateTime?
)