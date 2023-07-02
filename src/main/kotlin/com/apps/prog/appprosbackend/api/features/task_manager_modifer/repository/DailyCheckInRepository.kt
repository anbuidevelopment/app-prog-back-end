package com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository

import com.apps.prog.appprosbackend.api.features.authentication.model.LmsUser
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsDailyCheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyCheckInRepository : JpaRepository<LmsDailyCheckIn, Int> {
    fun findAllByUserAndCheckInDateAndIsCompleted(
        user: LmsUser,
        dueDate: String,
        isComplete: Int
    ): List<LmsDailyCheckIn>
}