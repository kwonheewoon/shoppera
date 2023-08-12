package org.khw.kotlinspring.config.exception

import org.khw.kotlinspring.common.exception.CategoryException
import org.khw.kotlinspring.common.exception.ItemException
import org.khw.kotlinspring.common.exception.UserException
import org.khw.kotlinspring.common.response.ErrCommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    // Custom Exception
    @ExceptionHandler(ItemException::class)
    fun itemException(ex: ItemException): ResponseEntity<ErrCommonResponse>{
        return ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)

    }

    @ExceptionHandler(CategoryException::class)
    fun categoryException(ex: CategoryException): ResponseEntity<ErrCommonResponse>{
        return ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)

    }

    @ExceptionHandler(UserException::class)
    fun userException(ex: UserException): ResponseEntity<ErrCommonResponse>{
        return ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)

    }

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