package com.apps.prog.appprosbackend.api.features.task_manager_modifer.repository

import com.apps.prog.appprosbackend.api.features.authentication.model.LmsUser
import com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.LmsTask
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TaskRepository : JpaRepository<LmsTask, Long> {

}