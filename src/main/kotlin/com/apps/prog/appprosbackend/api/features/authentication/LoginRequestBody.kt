package com.apps.prog.appprosbackend.api.features.authentication

data class LoginRequestBody(
    val username: String,
    val password: String
)
