package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model

import com.apps.prog.appprosbackend.api.features.authentication.model.LmsUser
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "lms_daily_check_ins")
data class LmsDailyCheckIn(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_in_id")
    val checkInId: Int = 0,

//    @ManyToOne
//    @JoinColumn(name = "task_id", nullable = false)
//    val task: LmsTaskAssignment,

    @Column(name = "task_id")
    val taskId: Int,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: LmsUser,

    @Column(name = "start_time")
    val startTime: String? = null,

    @Column(name = "end_time")
    val endTime: String? = null,

    @Column(name = "check_in_date", nullable = false)
    val checkInDate: String,

    @Column(name = "is_completed", nullable = false)
    val isCompleted: Int = 0
)