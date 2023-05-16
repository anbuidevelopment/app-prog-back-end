package com.apps.prog.appprosbackend.api.features.task_manager.controller

import com.apps.prog.appprosbackend.api.common.ApiResponse
import com.apps.prog.appprosbackend.api.features.authentication.User
import com.apps.prog.appprosbackend.api.features.task_manager.model.AssignTaskRequest
import com.apps.prog.appprosbackend.api.features.task_manager.model.Task
import com.apps.prog.appprosbackend.api.features.task_manager.model.TaskDto
import com.apps.prog.appprosbackend.api.features.task_manager.service.TaskAssignService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/task")
class TaskAssignController(private val service: TaskAssignService) {
    private val logger = LoggerFactory.getLogger(TaskAssignController::class.java)

    @GetMapping("/user/{userId}")
    fun getTasksByUserId(@PathVariable userId: Long): ResponseEntity<Any> {
        return try {
            val tasks = service.getTasksByUserId(userId)
            ResponseEntity.ok(tasks)
        } catch (e: Exception) {
            logger.error(e.localizedMessage)

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse(
                    status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    message = "Failed",
                    data = null
                )
            )
        }
    }

    @PostMapping("/assign")
    fun assignTask(
        @RequestBody assignTaskRequest: AssignTaskRequest,
    ): ResponseEntity<ApiResponse<Any>> {
        return try {
            val taskResult = service.assignTask(assignTaskRequest.task, assignTaskRequest.user)
            val response = ApiResponse<Any>(HttpStatus.OK.value(), "Task assigned successfully", taskResult)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            logger.error(e.localizedMessage)
            val response = ApiResponse<Any>(HttpStatus.BAD_REQUEST.value(), e.message ?: "Failed to assigned Task")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        } catch (e: Exception) {
            logger.error(e.localizedMessage)
            val response =
                ApiResponse<Any>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message ?: "Failed to assigned Task")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
        }
    }
}
