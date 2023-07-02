package com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository

import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsNormalCheckIn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NormalCheckInRepository : JpaRepository<LmsNormalCheckIn, Int> {
    fun findAllByTaskIdAndDueDateAndIsCompleted(taskId: Int, dueDate: String, isCompleted: Int): List<LmsNormalCheckIn>
}