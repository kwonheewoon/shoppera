package org.khw.shoppera.order.controller

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.service.OrderService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderRestController(
    val orderService: OrderService
) {

    @PostMapping
    fun orderConfirm(@RequestBody orderRequestApiDto: OrderRequestApiDto): ResponseEntity<CommonResponse<OrderViewApiDto>> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_REQUEST, orderService.orderRequest(orderRequestApiDto)))
    }
}