package org.khw.shoppera.order.controller

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.service.OrderService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @PatchMapping("/{orderNumber}/payment-confirm")
    fun orderPaymentConfirm(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
            orderService.orderPaymentConfirm(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_PAYMENT_CONFIRM))
    }

    @PatchMapping("/{orderNumber}/shipment-request")
    fun orderShipmentRequest(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
        orderService.orderShipmentRequest(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_SHIPMENT_REQUEST))
    }

    @PatchMapping("/{orderNumber}/shipment-process")
    fun orderShipmentProcess(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
        orderService.orderShipmentProcess(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_SHIPMENT_PROCESS))
    }

    @PatchMapping("/{orderNumber}/shipment-completed")
    fun orderShipmentCompleted(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
        orderService.orderShipmentCompleted(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_SHIPMENT_COMPLETED))
    }
}