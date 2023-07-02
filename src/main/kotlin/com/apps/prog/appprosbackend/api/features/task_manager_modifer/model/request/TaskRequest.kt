package com.apps.prog.appprosbackend.api.features.task_manager_modifer.model.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class TaskRequest @JsonCreator constructor(
    @JsonProperty("taskName") val taskName: String,
    @JsonProperty("taskDescription") val taskDescription: String?,
    @JsonProperty("typeId") val typeId: Int,
    @JsonProperty("dueDate") val dueDate: String? = null,
    @JsonProperty("startTime") val startTime: String? = null,
    @JsonProperty("endTime") val endTime: String? = null,
    @JsonProperty("assignRequest") val assignRequest: AssignRequest,
    @JsonProperty("priority") val priority: Int = 0,
    @JsonProperty("status") val status: Int = 0,
    @JsonProperty("note") val note: String?
)


data class AssignRequest @JsonCreator constructor(
    @JsonProperty("assignedByUser") val assignedByUser: Int,
    @JsonProperty("assignedToUsers") val assignedToUsers: List<Int>
)
