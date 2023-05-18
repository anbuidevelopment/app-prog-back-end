package com.apps.prog.appprosbackend.api.features.task_manager.service

import com.apps.prog.appprosbackend.api.features.authentication.User
import com.apps.prog.appprosbackend.api.features.task_manager.model.Status
import com.apps.prog.appprosbackend.api.features.task_manager.model.Task
import com.apps.prog.appprosbackend.api.features.task_manager.model.TaskAssignment
import com.apps.prog.appprosbackend.api.features.task_manager.model.dto.TaskDto
import com.apps.prog.appprosbackend.api.features.task_manager.repository.TaskAssignRepository
import org.springframework.stereotype.Service


@Service
class TaskAssignService(private val repository: TaskAssignRepository) {
    fun getTasksByUserId(userId: Long): List<TaskDto> {
        return repository.findByUser_Id(userId = userId)
    }

    fun assignTask(task: Task, user: User) {
        val taskAssignment = TaskAssignment(task = task, user = user, status = Status.NEW)
        repository.save(taskAssignment)
    }
}