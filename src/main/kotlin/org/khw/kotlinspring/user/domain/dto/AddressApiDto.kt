package org.khw.kotlinspring.user.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import org.hibernate.annotations.Comment

class AddressApiDto (

    @Schema(description = "기본 주소", example = "인천 OO구 OO동")
    val address : String,

    @Schema(description = "상세 주소", example = "OO 아파트 OO동 OO호")
    val addressDetail : String,

    @Schema(description = "우편번호", example = "68544")
    val zipCode : String
    ) {
}