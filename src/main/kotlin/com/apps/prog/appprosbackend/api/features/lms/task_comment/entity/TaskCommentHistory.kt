package com.apps.prog.appprosbackend.api.features.lms.task_comment.entity

import jakarta.persistence.*

@Entity(name = "TaskCommentHistory")
@Table(name = "TaskCommentHistory", schema = "lms")
data class TaskCommentHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskCommentHistoryId")
    val taskCommentHistoryId: Long? = null,

    @Column(name = "TaskCommentId")
    val taskCommentId: Long,

    @Column(name = "AccountId")
    val accountId: Int,

    @Column(name = "Content", nullable = false)
    val content: String?,


)
