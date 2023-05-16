package com.apps.prog.appprosbackend.api.common

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T? = null
)