package com.apps.prog.appprosbackend.api.features.task_manager.controller

import com.apps.prog.appprosbackend.api.common.ApiResponse
import com.apps.prog.appprosbackend.api.features.task_manager.model.Task
import com.apps.prog.appprosbackend.api.features.task_manager.service.TaskAssignService
import com.apps.prog.appprosbackend.api.features.task_manager.service.TaskService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/task")
class TaskController(
    private val taskService: TaskService,
    private val taskAssignService: TaskAssignService
) {
    private val logger = LoggerFactory.getLogger(TaskController::class.java)

    @PostMapping
    fun newTask(
        @RequestBody task: Task
    ): ResponseEntity<Any> {
        return try {
            val taskResult = taskService.newTask(task)
            ResponseEntity.ok(taskResult)
        } catch (e: IllegalArgumentException) {
            logger.error(e.localizedMessage)
            val response = ApiResponse<Any>(HttpStatus.BAD_REQUEST.value(), e.message ?: "Failed to create Task")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        } catch (e: Exception) {
            logger.error(e.localizedMessage)
            val response =
                ApiResponse<Any>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message ?: "Failed to create Task")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
        }
    }
}