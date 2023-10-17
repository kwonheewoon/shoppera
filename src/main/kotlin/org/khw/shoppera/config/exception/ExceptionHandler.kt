package org.khw.shoppera.config.exception

import org.khw.shoppera.common.exception.*
import org.khw.shoppera.common.response.ErrCommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    // 도메인 객체별 Custom Exception
    @ExceptionHandler(value = [AuthoritiesException::class, UserException::class, CategoryException::class, ItemException::class,
        CouponException::class, BrandException::class,ItemTypeException::class])
    private fun userException(ex: RuntimeException): ResponseEntity<ErrCommonResponse>{
        return when(ex){
            is AuthoritiesException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            is UserException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            is CategoryException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            is ItemTypeException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            is ItemException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            is CouponException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            is BrandException -> ResponseEntity(ErrCommonResponse(ex.message, ex.code), ex.httpStatus)
            else -> ResponseEntity(ErrCommonResponse((ex as ItemOptionException).message, ex.code), ex.httpStatus)
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