package com.apps.prog.appprosbackend.api.features.lms.task_comment

import com.apps.prog.appprosbackend.api.features.lms.task_comment.model.TaskCommentRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/taskComment")
@RestController
class TaskCommentController(private val service: TaskCommentService) {
    @GetMapping
    fun getAllCommentByTaskId(@RequestParam("taskId") taskId: Long) = service.findAllCommentByTaskId(taskId)

    @PostMapping
    fun saveOrUpdateComment(@RequestBody body: TaskCommentRequest) = service.save(body)
}