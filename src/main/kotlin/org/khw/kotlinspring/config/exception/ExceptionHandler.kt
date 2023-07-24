package org.khw.kotlinspring.config.exception

import org.khw.kotlinspring.common.response.ErrCommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<List<String>> {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage ?: "Unknown error" }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrCommonResponse>{
        return ResponseEntity(ErrCommonResponse(ex.message, "500"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}