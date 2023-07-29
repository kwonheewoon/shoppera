package org.khw.kotlinspring.user.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.Comment

class AddressUpdateDto (

    @NotBlank(message = "기본 주소는 필수 값 입니다.")
    @Schema(description = "기본 주소", example = "인천 OO구 OO동")
    val address : String,

    @NotBlank(message = "상세 주소는 필수 값 입니다.")
    @Schema(description = "상세 주소", example = "OO 아파트 OO동 OO호")
    val addressDetail : String,

    @NotBlank(message = "우편변호는 필수 값 입니다.")
    @Schema(description = "우편번호", example = "68544")
    val zipCode : String
    ) {
}