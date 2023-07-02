package com.apps.prog.appprosbackend.api.features.task_manager_modifer.controller

import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.request.TaskRequest
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/task")
class TaskController(private val taskService: TaskService) {
    @GetMapping("/user/{userId}")
    fun getAllTasksForUser(@PathVariable userId: Int) =
        taskService.getAllTasksByUser(userId)

    @GetMapping("/user/{userId}/date/{dueDate}")
    fun getAllTasksByUserAndDate(@PathVariable userId: Int, @PathVariable dueDate: String) =
        taskService.getAllTasksByUserAndDate(userId, dueDate)


    @PostMapping("/save")
    fun saveTask(@RequestBody body: TaskRequest) = taskService.createTask(body)

    @PutMapping("/update/{taskId}/{status}")
    fun updateTaskStatus(@PathVariable taskId: Long, @PathVariable status: Int) =
        taskService.updateTaskStatus(status = status, taskId = taskId)

    @GetMapping("/dept/{dept}")
    fun getAllTasksInDepartment(@PathVariable dept: String) = taskService.getTaskOfUsersInDepartment(department = dept)
}
