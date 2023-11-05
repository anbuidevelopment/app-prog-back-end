package com.apps.prog.appprosbackend.api.features.authentication.model

data class ChangePasswordRequest(
    val id: Int,
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String,
)
