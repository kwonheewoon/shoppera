package org.khw.shoppera.usercoupon.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UserCouponSaveApiDto(

    @Schema(description = "유저 아이디(pk)", example = "1")
    val userId: Long,

    @Schema(description = "쿠폰 아이디", example = "20")
    val couponId: Long
)