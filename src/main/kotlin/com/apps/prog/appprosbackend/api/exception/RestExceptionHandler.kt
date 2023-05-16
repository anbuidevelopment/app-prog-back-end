package com.apps.prog.appprosbackend.api.exception

import com.apps.prog.appprosbackend.api.common.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.http.HttpConnectTimeoutException
import java.net.http.HttpTimeoutException

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        ex: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Any>> {
        val response = ApiResponse<Any>(HttpStatus.BAD_REQUEST.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(HttpMediaTypeException::class)
    fun handleHttpMediaTypeException(ex: HttpMediaTypeException): ResponseEntity<ApiResponse<Any>> {
        val response =
            ApiResponse<Any>(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(response)
    }

    @ExceptionHandler(HttpTimeoutException::class)
    fun handleHttpTimeoutException(ex: HttpTimeoutException): ResponseEntity<ApiResponse<Any>> {
        val response = ApiResponse<Any>(HttpStatus.REQUEST_TIMEOUT.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(response)
    }

    @ExceptionHandler(HttpServerErrorException::class)
    fun handleHttpServerErrorException(ex: HttpServerErrorException): ResponseEntity<ApiResponse<Any>> {
        val response =
            ApiResponse<Any>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }

    @ExceptionHandler(HttpConnectTimeoutException::class)
    fun handleHttpConnectTimeoutException(ex: HttpConnectTimeoutException): ResponseEntity<ApiResponse<Any>> {
        val response = ApiResponse<Any>(HttpStatus.REQUEST_TIMEOUT.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ApiResponse<Any>> {
        val response = ApiResponse<Any>(HttpStatus.METHOD_NOT_ALLOWED.value(), "${ex.message}")
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

}