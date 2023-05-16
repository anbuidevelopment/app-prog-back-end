package com.apps.prog.appprosbackend.api.features.task_manager.service

import com.apps.prog.appprosbackend.api.features.task_manager.model.Task
import com.apps.prog.appprosbackend.api.features.task_manager.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val repository: TaskRepository) {

    fun newTask(task: Task): Long {
        val savedTask = repository.save(task)
        return savedTask.id
    }
}