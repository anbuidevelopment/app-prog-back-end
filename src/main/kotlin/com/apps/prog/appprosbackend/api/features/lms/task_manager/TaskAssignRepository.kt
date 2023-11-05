package com.apps.prog.appprosbackend.api.features.lms.task_manager

import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.TaskAssignment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TaskAssignRepository: JpaRepository<TaskAssignment, Long> {
    fun deleteByTaskId(taskId: Long)

    @Query("SELECT ta.assignedToAccountId FROM TaskAssignments ta WHERE ta.taskId = :taskId")
    fun findAssignedAccountIdsByTaskId(@Param("taskId") taskId: Long): List<Int>
}