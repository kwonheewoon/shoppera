package org.khw.kotlinspring.user.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import lombok.Getter
import org.khw.kotlinspring.common.CommonEnum.*
import java.time.LocalDate
data class UserUpdateDto(

    @field:NotBlank(message = "회원 이름은 필수 값 입니다.")
    @Schema(description = "회원 이름", example = "권희운")
    val name : String,

    @NotBlank(message = "회원 아이디는 필수 값 입니다.")
    @Schema(description = "회원 아이디", example = "gmldns46")
    val accountId : String,

    val address : AddressUpdateDto,

    @NotBlank(message = "회원 휴대폰번호는 필수 값 입니다.")
    @Schema(description = "회원 휴대폰번호", example = "010-1111-2222")
    val phoneNumber : String,
) {
}