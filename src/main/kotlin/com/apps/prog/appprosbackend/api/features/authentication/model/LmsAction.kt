package com.apps.prog.appprosbackend.api.features.authentication.model

import jakarta.persistence.*

@Entity
@Table(name = "lms_actions")
data class LmsAction(
    @Id
    @Column(name = "action_id")
    val actionId: Int? = null,

    @Column(name = "action_name")
    val actionName: String? = null
)
