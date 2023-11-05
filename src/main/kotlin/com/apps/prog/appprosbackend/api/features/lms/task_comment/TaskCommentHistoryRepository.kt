package com.apps.prog.appprosbackend.api.features.lms.task_comment

import com.apps.prog.appprosbackend.api.features.lms.task_comment.entity.TaskCommentHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskCommentHistoryRepository : JpaRepository<TaskCommentHistory, Long> {
}