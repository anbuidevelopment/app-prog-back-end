package com.apps.prog.appprosbackend.utils

import com.apps.prog.appprosbackend.api.common.ApiErrorResponse
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomErrorController : ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<ApiErrorResponse> {
        val status = response.status
        val errorMessage = HttpStatus.valueOf(status)
        val errorResponse = ApiErrorResponse(status, errorMessage.name)
        return ResponseEntity.status(status).body(errorResponse)
    }


    private fun getErrorMessage(request: HttpServletRequest): String {
        return request.getAttribute(RequestDispatcher.ERROR_MESSAGE) as? String ?: "Unknown error"
    }
}