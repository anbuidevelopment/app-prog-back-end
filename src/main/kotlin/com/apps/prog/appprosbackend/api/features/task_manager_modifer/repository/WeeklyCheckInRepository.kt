package com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository

import com.apps.prog.appprosbackend.api.features.authentication.model.LmsUser
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsWeeklyCheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WeeklyCheckInRepository : JpaRepository<LmsWeeklyCheckIn, Int> {
    fun findAllByUserAndCheckInWeekAndIsCompleted(
        user: LmsUser,
        dueDate: String,
        isCompleted: Int
    ): List<LmsWeeklyCheckIn>
}