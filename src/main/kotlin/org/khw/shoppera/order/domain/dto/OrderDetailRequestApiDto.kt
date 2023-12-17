package org.khw.shoppera.order.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

data class OrderDetailRequestApiDto (

    @Schema(description = "아이템 아이디", example = "1")
    val itemId: Long,

    @Schema(description = "주문 가격", example = "65000")
    val price: Int,

    @Schema(description = "주문 수량", example = "3")
    val quantity: Int,

    @Schema(description = "쿠폰 아이디", example = "45")
    val couponId: Long
)