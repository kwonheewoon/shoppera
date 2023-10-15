package org.khw.shoppera.common.exception

import org.khw.shoppera.common.enums.ResCode
import org.springframework.http.HttpStatus

class BrandException(resCode: ResCode) : RuntimeException(resCode.message) {
    val code: String
    val httpStatus: HttpStatus

    init {
        this.code = resCode.code
        this.httpStatus = resCode.httpStatus
    }

}