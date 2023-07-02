package com.apps.prog.appprosbackend.api.features.authentication.model

import jakarta.persistence.*

@Entity
@Table(name = "lms_roles")
data class LmsRole(
    @Id
    @Column(name = "role_id")
    val roleId: Int? = null,

    @Column(name = "role_name")
    val roleName: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "lms_role_actions",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "action_id")]
    )
    val actions: List<LmsAction> = mutableListOf()
)