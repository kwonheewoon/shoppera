package org.khw.kotlinspring.user.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.khw.kotlinspring.common.CommonEnum.*
import java.time.LocalDate

data class UserSaveDto(

    @field:NotBlank(message = "회원 이름은 필수 값 입니다.")
    @Schema(description = "회원 이름", example = "권희운")
    val name : String,

    @field:NotBlank(message = "회원 아이디는 필수 값 입니다.")
    @Schema(description = "회원 아이디", example = "gmldns46")
    val accountId : String,

    @field:NotNull(message = "회원 생년월일은 필수 값 입니다.")
    @Schema(description = "회원 생년월일", example = "970724")
    @JsonFormat(pattern = "yyMMdd")
    val birthDate : LocalDate,

    val address : AddressSaveDto,

    @field:NotBlank(message = "회원 휴대폰번호는 필수 값 입니다.")
    @Schema(description = "회원 휴대폰번호", example = "010-1111-2222")
    val phoneNumber : String,

    val deleteFlag: FlagYn = FlagYn.N
) {
}