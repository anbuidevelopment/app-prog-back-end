package com.apps.prog.appprosbackend.api.features.task_manager.repository

import com.apps.prog.appprosbackend.api.features.task_manager.model.TaskAssignment
import com.apps.prog.appprosbackend.api.features.task_manager.model.TaskAssignmentId
import com.apps.prog.appprosbackend.api.features.task_manager.model.TaskDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TaskAssignRepository : JpaRepository<TaskAssignment, TaskAssignmentId> {

    @Query(
        "SELECT NEW com.apps.prog.appprosbackend.api.features.task_manager.model.TaskDto("
                + "ta.task.id, "
                + "ta.task.title, "
                + "ta.task.description, "
                + "ta.task.dueDate, "
                + "ta.task.timeRange, "
                + "ta.task.type.name, "
                + "ta.task.priority, "
                + "ta.task.startTime, "
                + "ta.task.endTime, "
                + "ta.task.status, "
                + "ta.task.note) "
                + "FROM lms_task_assignment ta "
                + "WHERE ta.user.id = :userId"
    )
    fun findByUser_Id(userId: Long): List<TaskDto>



}