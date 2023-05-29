package org.khw.kotlinspring.user.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.*
import java.time.LocalDate

data class UserSaveDto(
    @Schema(description = "회원 이름", example = "권희운")
    val name : String,

    @Schema(description = "회원 아이디", example = "gmldns46")
    val accountId : String,

    @Schema(description = "회원 생년월일", example = "970724")
    @JsonFormat(pattern = "yyMMdd")
    val birthDate : LocalDate,

    val address : AddressDto,

    @Schema(description = "회원 휴대폰번호", example = "010-1111-2222")
    val phoneNumber : String,

    val deleteFlag: FlagYn
) {
}