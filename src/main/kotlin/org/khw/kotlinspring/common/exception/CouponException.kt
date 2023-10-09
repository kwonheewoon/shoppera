package org.khw.kotlinspring.common.exception

import org.khw.kotlinspring.common.enums.ResCode
import org.springframework.http.HttpStatus

class CouponException(resCode: ResCode) : RuntimeException(resCode.message) {
    val code: String
    val httpStatus: HttpStatus

    init {
        this.code = resCode.code
        this.httpStatus = resCode.httpStatus
    }

}