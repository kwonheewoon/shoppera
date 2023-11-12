package org.khw.shoppera.order.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.common.response.ErrCommonResponse
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
@Tag(name = "Order RestController", description = "주문 관련 API")
class OrderRestController(
    val orderService: OrderService
) {

    @PostMapping
    @Operation(summary = "주문 요청 등록", description = "이용자의 주문 요청을 등록 한다.", responses = [
        ApiResponse(
            responseCode = "200_421",
            description = "주문 요청이 정상적으로 완료되었습니다.",
            content = [Content(schema = Schema(implementation = OrderViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "200_420",
            description = "주문 요청 실패하였습니다. 주문정보를 확인해 주세요.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun orderConfirm(@RequestBody orderRequestApiDto: OrderRequestApiDto): ResponseEntity<CommonResponse<OrderViewApiDto>> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_REQUEST, orderService.orderRequest(orderRequestApiDto)))
    }

    @Operation(summary = "주문 결제 확인", description = "이용자의 주문 상태를 결제 확인으로 변경한다.", responses = [
        ApiResponse(
            responseCode = "200_426",
            description = "주문 결제가 확인 되었습니다.",
            content = [Content(schema = Schema(implementation = OrderViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "200_422",
            description = "주문 요청 상태가 아닙니다. 상태를 확인해 주세요.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    @PatchMapping("/{orderNumber}/payment-confirm")
    fun orderPaymentConfirm(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
            orderService.orderPaymentConfirm(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_PAYMENT_CONFIRM))
    }

    @Operation(summary = "주문 출고 요청", description = "이용자의 주문 상태를 출고 요청으로 변경한다.", responses = [
        ApiResponse(
            responseCode = "200_427",
            description = "주문 출고 요청이 되었습니다.",
            content = [Content(schema = Schema(implementation = OrderViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "200_423",
            description = "입금 확인 상태가 아닙니다. 상태를 확인해 주세요.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    @PatchMapping("/{orderNumber}/shipment-request")
    fun orderShipmentRequest(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
        orderService.orderShipmentRequest(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_SHIPMENT_REQUEST))
    }

    @Operation(summary = "주문 출고 처리", description = "이용자의 주문 상태를 출고 처리로 변경한다.", responses = [
        ApiResponse(
            responseCode = "200_428",
            description = "주문 출고 처리 되었습니다.",
            content = [Content(schema = Schema(implementation = OrderViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "200_424",
            description = "출고 요청 상태가 아닙니다. 상태를 확인해 주세요.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    @PatchMapping("/{orderNumber}/shipment-process")
    fun orderShipmentProcess(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
        orderService.orderShipmentProcess(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_SHIPMENT_PROCESS))
    }

    @Operation(summary = "주문 출고 완료", description = "이용자의 주문 상태를 출고 완료로 변경한다.", responses = [
        ApiResponse(
            responseCode = "200_429",
            description = "주문 출고 완료 되었습니다.",
            content = [Content(schema = Schema(implementation = OrderViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "200_425",
            description = "출고 처리 상태가 아닙니다. 상태를 확인해 주세요.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    @PatchMapping("/{orderNumber}/shipment-completed")
    fun orderShipmentCompleted(@PathVariable("orderNumber") orderNumber: String): ResponseEntity<CommonResponse<Unit>>{
        orderService.orderShipmentCompleted(orderNumber)
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.ORDER_WITH_SHIPMENT_COMPLETED))
    }
}