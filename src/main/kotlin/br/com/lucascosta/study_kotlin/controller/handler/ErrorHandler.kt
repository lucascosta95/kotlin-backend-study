package br.com.lucascosta.study_kotlin.controller.handler

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun illegalArgumentException(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(statusCode = HttpStatus.BAD_REQUEST.value(), message = exception.message!!)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}

data class ErrorResponse(val statusCode: Int, val message: String)