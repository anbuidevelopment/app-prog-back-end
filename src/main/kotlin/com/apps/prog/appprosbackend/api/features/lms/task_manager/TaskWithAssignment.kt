package com.apps.prog.appprosbackend.api.features.lms.task_manager

import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.Task
import com.apps.prog.appprosbackend.api.features.lms.task_manager.entity.TaskAssignment

interface TaskWithAssignment {
    fun getTask(): Task
    fun getTaskAssignment(): TaskAssignment
}
