package com.apps.prog.appprosbackend.api.features.authentication.model.request

data class LoginRequestBody(
    val username: String,
    val password: String
)
