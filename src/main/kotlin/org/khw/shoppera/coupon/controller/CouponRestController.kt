package org.khw.shoppera.coupon.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.common.response.ErrCommonResponse
import org.khw.shoppera.coupon.domain.dto.CouponSaveApiDto
import org.khw.shoppera.coupon.domain.dto.CouponUpdateApiDto
import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import org.khw.shoppera.coupon.service.CouponService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("coupon")
@RequiredArgsConstructor
@Tag(name = "Coupon RestController", description = "쿠폰 관련 API")
class CouponRestController(val couponService: CouponService) {

    @PostMapping
    @Operation(summary = "쿠폰 등록", description = "쿠폰의 정보를 등록 한다.", responses = [
        ApiResponse(
            responseCode = "200_408",
            description = "쿠폰 정보가 등록 되었습니다.",
            content = [Content(schema = Schema(implementation = CouponViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "409_004",
            description = "중복된 쿠폰 이름 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_009",
            description = "존재 하지 않는 브랜드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun saveCoupon(@RequestBody couponSaveApiDto: CouponSaveApiDto): ResponseEntity<CommonResponse<CouponViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.COUPON_SAVE, couponService.saveCoupon(couponSaveApiDto)))
    }

    @PutMapping("/{couponId}")
    @Operation(summary = "쿠폰 수정", description = "쿠폰의 정보를 수정 한다.", responses = [
        ApiResponse(
            responseCode = "200_409",
            description = "쿠폰 정보가 수정 되었습니다.",
            content = [Content(schema = Schema(implementation = CouponViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "409_004",
            description = "중복된 쿠폰 이름 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_008",
            description = "존재 하지 않는 쿠폰 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        ),
        ApiResponse(
            responseCode = "404_009",
            description = "존재 하지 않는 브랜드 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun updateCoupon(@PathVariable couponId: Long, @RequestBody couponUpdateApiDto: CouponUpdateApiDto): ResponseEntity<CommonResponse<CouponViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.COUPON_UPDATE, couponService.updateCoupon(couponId, couponUpdateApiDto)))
    }

    @DeleteMapping("/{couponId}")
    @Operation(summary = "쿠폰 수정", description = "쿠폰의 정보를 수정 한다.", responses = [
        ApiResponse(
            responseCode = "200_410",
            description = "쿠폰 정보가 삭제 되었습니다.",
            content = [Content(schema = Schema(implementation = CouponViewApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_008",
            description = "존재 하지 않는 쿠폰 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )

    ])
    fun deleteCoupon(@PathVariable couponId: Long): ResponseEntity<CommonResponse<Unit>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.COUPON_DELETE, couponService.deleteCoupon(couponId)))
    }
}