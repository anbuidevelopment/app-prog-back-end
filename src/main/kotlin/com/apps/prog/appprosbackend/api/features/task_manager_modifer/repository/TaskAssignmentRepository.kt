package com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository

import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsTaskAssignment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskAssignmentRepository: JpaRepository<LmsTaskAssignment, Long> {
}