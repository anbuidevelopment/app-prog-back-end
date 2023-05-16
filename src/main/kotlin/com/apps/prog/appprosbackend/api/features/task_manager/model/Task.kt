package com.apps.prog.appprosbackend.api.features.task_manager.model

import com.apps.prog.appprosbackend.api.features.authentication.User
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "lms_task")
@Table(name = "lms_task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    val id: Long = 0,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "due_date")
    val dueDate: LocalDate,

    @Column(name = "time_range")
    val timeRange: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    val type: TaskType,

    @Column(name = "priority")
    val priority: Int = 0,

    @Column(name = "start_time")
    val startTime: LocalDateTime? = null,

    @Column(name = "end_time")
    val endTime: LocalDateTime? = null,

    @Column(name = "status")
    val status: Status = Status.NEW,

    @Column(name = "note")
    val note: String? = null
)


enum class Status(type: Int) {
    NEW(0), IN_PROGRESS(1), PENDING(2), COMPLETE(4)
}