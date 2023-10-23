package org.khw.shoppera.order.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

data class OrderDetailSaveApiDto (
    @Schema(description = "주문 가격", example = "65000")
    val price: Int,

    @Schema(description = "주문 수량", example = "3")
    val quantity: Int,

    @Schema(description = "주문 상태", example = "{ORDER_CONFIRM, PAYMENT_CONFIRM, SHIPMENT_REQUEST, SHIPMENT_PROCESS, SHIPMENT_COMPLETED}")
    val state: String
)