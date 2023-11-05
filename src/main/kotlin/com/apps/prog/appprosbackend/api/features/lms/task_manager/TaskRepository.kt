package com.apps.prog.appprosbackend.api.features.lms.task_manager

import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.Task
import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.TaskAssignment
import com.apps.prog.appprosbackend.api.features.lms.task_manager.model.TaskResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
//    @Query("SELECT DISTINCT NEW com.apps.prog.appprosbackend.api.features.lms.task_manager.model.TaskResponse(t.taskId, t.title, t.description, t.dueDate, t.deadlineTime, t.status, t.priority, ts.assignedByAccountId ,t.createdBy, t.createdDate, t.updatedBy, t.updatedDate) FROM Tasks t INNER JOIN TaskAssignments ts ON t.taskId = ts.taskId WHERE ts.assignedByAccountId = :accountId OR ts.assignedToAccountId = :accountId")
//    fun findTasksByAccountId(@Param("accountId") accountId: Int): List<TaskResponse>

    @Query("SELECT t AS task, ts AS taskAssignment FROM Tasks t INNER JOIN TaskAssignments ts ON t.taskId = ts.taskId WHERE ts.assignedByAccountId = :accountId OR ts.assignedToAccountId = :accountId")
    fun findTasksAndAssignmentsByAccountId(@Param("accountId") accountId: Int): List<TaskWithAssignment>

}

