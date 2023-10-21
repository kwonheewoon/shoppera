package org.khw.shoppera.usercoupon.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class UserCouponViewApiDto(
    @Schema(description = "유저 쿠폰 아이디", example = "1")
    val userCouponId: Long,

    @Schema(description = "쿠폰 아이디", example = "20")
    val couponId: Long,

    @Schema(description = "쿠폰 이름", example = "쿠폰 이름")
    val couponName: String,

    @Schema(description = "쿠폰 할인율", example = "35")
    val discountRate: Int,

    @Schema(description = "쿠폰 만료일", example = "2023.11.01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    val expireDate: LocalDate
)