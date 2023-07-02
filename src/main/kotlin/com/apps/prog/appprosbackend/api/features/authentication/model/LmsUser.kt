package com.apps.prog.appprosbackend.api.features.authentication.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity(name = "lms_users")
@Table(name = "lms_users")
data class LmsUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Int,

    @Column(name = "username")
    val username: String,

    @Column(name = "fullname")
    val fullName: String,

    @Column(name = "emp_code")
    val empCode: String,

    @Column(name = "password")
    @JsonIgnore
    val password: String,

    val gender: Int,

    val department: String,

    val country: String,

    val phone: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    val role: LmsRole? = null
)

