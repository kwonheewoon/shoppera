package org.khw.kotlinspring.config.exception

import org.khw.kotlinspring.common.exception.AuthoritiesException
import org.khw.kotlinspring.common.exception.CategoryException
import org.khw.kotlinspring.common.exception.ItemException
import org.khw.kotlinspring.common.exception.UserException
import org.khw.kotlinspring.common.response.ErrCommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    // 도메인 객체별 Custom Exception
    @ExceptionHandler(value = [AuthoritiesException::class, UserException::class, CategoryException::class, ItemException::class])
    private fun userException(ex: RuntimeException): ResponseEntity<ErrCommonResponse>{
        return when(ex){
            is AuthoritiesException -> ResponseEntity(ErrCommonResponse((ex as AuthoritiesException).message, ex.code), ex.httpStatus)
            is UserException -> ResponseEntity(ErrCommonResponse((ex as UserException).message, ex.code), ex.httpStatus)
            is CategoryException -> ResponseEntity(ErrCommonResponse((ex as CategoryException).message, ex.code), ex.httpStatus)
            else -> ResponseEntity(ErrCommonResponse((ex as ItemException).message, ex.code), ex.httpStatus)
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    private fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<List<String>> {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage ?: "Unknown error" }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalStateException::class)
    private fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrCommonResponse>{
        return ResponseEntity(ErrCommonResponse(ex.message, "500"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}