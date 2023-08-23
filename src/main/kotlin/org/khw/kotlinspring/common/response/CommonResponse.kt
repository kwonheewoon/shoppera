package org.khw.kotlinspring.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import org.khw.kotlinspring.common.enums.ResCode


data class CommonResponse<T>(
    var message: String,

    var code: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var result: T? = null

) {
    constructor(code: String, message: String) : this(message, code, null)

    constructor(resCode: ResCode, parameter: T?) : this(resCode.message, resCode.code, parameter)

    constructor(resCode: ResCode) : this(resCode.message, resCode.code, null)
}
