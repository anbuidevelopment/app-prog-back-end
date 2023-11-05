package com.apps.prog.appprosbackend.api.features.lms.task_manager

import com.apps.prog.appprosbackend.api.features.lms.task_manager.model.TaskRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/task")
class TaskController(private val taskService: TaskService) {
    @PostMapping("/save")
    fun createTask(@RequestBody body: TaskRequest) = taskService.save(body)

    @GetMapping("/tasksByAccountId")
    fun getTasksByAccountId(@RequestParam("accountId") accountId: Int) = taskService.findTasksByAccountId(accountId)
}