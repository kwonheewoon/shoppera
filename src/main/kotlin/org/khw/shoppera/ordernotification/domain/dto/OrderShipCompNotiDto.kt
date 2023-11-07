package org.khw.shoppera.ordernotification.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class OrderShipCompNotiDto(
    @Schema(description = "주문 번호", example = "202310231945300001")
    val orderNumber: String,

    @Schema(description = "주문 일자", example = "2023-10-23")
    @JsonFormat(pattern = "yyyy-MM-dd")
    val orderDate: LocalDate,

    @Schema(description = "상세 주문 정보", example = "[{" +
            "price : 65000" +
            "quantity : 3" +
            "state : \"ORDER_CONFIRM\"" +
            "orderDateTime : \"2023-10-23 19:45:30\"" +
            "}]")
    val orderDetailList: List<OrderDetailDto>
)