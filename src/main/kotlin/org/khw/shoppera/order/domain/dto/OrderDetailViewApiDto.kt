package org.khw.shoppera.order.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import java.time.LocalDateTime

data class OrderDetailViewApiDto (
    @Schema(description = "주문 아이템 정보", example = "")
    val item: ItemViewApiDto,

    @Schema(description = "주문 가격", example = "65000")
    val price: Int,

    @Schema(description = "주문 수량", example = "3")
    val quantity: Int,

    @Schema(description = "주문 상태", example = "{ORDER_CONFIRM, PAYMENT_CONFIRM, SHIPMENT_REQUEST, SHIPMENT_PROCESS, SHIPMENT_COMPLETED}")
    val state: String,

    @Schema(description = "주문 시각", example = "2023-10-23 19:45:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val orderDateTime: LocalDateTime
)