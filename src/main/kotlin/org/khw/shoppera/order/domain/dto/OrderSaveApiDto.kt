package org.khw.shoppera.order.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

data class OrderSaveApiDto(
    @Schema(description = "유저 아이디(PK)", example = "1")
    val userId: Long,

    @Schema(description = "상세 주문 정보", example = "[{" +
            "price : 65000" +
            "quantity : 3" +
            "state : \"ORDER_CONFIRM\"" +
            "}]")
    val orderDetailList: List<OrderDetailSaveApiDto>
)