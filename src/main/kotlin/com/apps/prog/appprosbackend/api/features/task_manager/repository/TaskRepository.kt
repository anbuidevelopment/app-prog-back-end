package com.apps.prog.appprosbackend.api.features.task_manager.repository

import com.apps.prog.appprosbackend.api.features.task_manager.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long>