package com.apps.prog.appprosbackend.api.features.lms.task_comment.entity

import jakarta.persistence.*

@Entity(name = "TaskComments")
@Table(name = "TaskComments", schema = "lms")
data class TaskComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskCommentId")
    val taskCommentId: Long? = null,

    @Column(name = "TaskId")
    val taskId: Long,

    @Column(name = "AccountId")
    val accountId: Int,

    @Column(name = "Content", nullable = false)
    val content: String,

    @Column(name = "ParentTaskCommentId")
    val parentTaskCommentId: Long?,

    @Column(name = "CreatedBy", nullable = false)
    val createdBy: Int,

    @Column(name = "CreatedDate", insertable = false, updatable = false)
    val createdDate: String,

    @Column(name = "UpdatedBy", nullable = false)
    val updatedBy: Int,

    @Column(name = "UpdatedDate", insertable = false, updatable = false)
    val updatedDate: String
)
