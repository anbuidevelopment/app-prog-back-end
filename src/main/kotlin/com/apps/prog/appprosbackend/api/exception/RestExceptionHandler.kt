package com.apps.prog.appprosbackend.api.exception

import com.apps.prog.appprosbackend.api.common.ApiErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import java.net.http.HttpConnectTimeoutException
import java.net.http.HttpTimeoutException

@RestControllerAdvice
class RestExceptionHandler {
//    @ExceptionHandler(Exception::class)
//    fun handleException(ex: Exception): ResponseEntity<ApiErrorResponse> {
//        val errorMessage = "An error occurred: ${ex.message}"
//        val response = ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage)
//        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
//    }

    @ExceptionHandler(AppException::class)
    fun handleAppException(ex: AppException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(AppAuthException::class)
    fun handleAppAuthException(ex: AppAuthException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        ex: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(HttpMediaTypeException::class)
    fun handleHttpMediaTypeException(ex: HttpMediaTypeException): ResponseEntity<ApiErrorResponse> {
        val response =
            ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(response)
    }

    @ExceptionHandler(HttpTimeoutException::class)
    fun handleHttpTimeoutException(ex: HttpTimeoutException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.REQUEST_TIMEOUT.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(response)
    }

    @ExceptionHandler(HttpServerErrorException::class)
    fun handleHttpServerErrorException(ex: HttpServerErrorException): ResponseEntity<ApiErrorResponse> {
        val response =
            ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }

    @ExceptionHandler(HttpConnectTimeoutException::class)
    fun handleHttpConnectTimeoutException(ex: HttpConnectTimeoutException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.REQUEST_TIMEOUT.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

    @ExceptionHandler(HttpClientErrorException::class)
    fun handleHttpClientErrorException(ex: HttpClientErrorException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.NOT_FOUND.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    };

    @ExceptionHandler(RuntimeException::class)
    fun handleRunTimeException(ex: RuntimeException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

}