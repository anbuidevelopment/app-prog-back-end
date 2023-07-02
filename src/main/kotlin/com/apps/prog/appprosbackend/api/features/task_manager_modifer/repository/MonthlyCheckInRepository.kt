package com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository

import com.apps.prog.appprosbackend.api.features.authentication.model.LmsUser
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsMonthlyCheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MonthlyCheckInRepository : JpaRepository<LmsMonthlyCheckIn, Int> {
    fun findAllByUserAndCheckInMonthAndIsCompleted(
        user: LmsUser,
        dueDate: String,
        isCompleted: Int
    ): List<LmsMonthlyCheckIn>
}