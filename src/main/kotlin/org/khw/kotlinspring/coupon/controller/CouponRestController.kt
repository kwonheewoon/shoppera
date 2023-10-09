package org.khw.kotlinspring.coupon.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.response.CommonResponse
import org.khw.kotlinspring.coupon.domain.dto.CouponSaveDto
import org.khw.kotlinspring.coupon.domain.dto.CouponViewApiDto
import org.khw.kotlinspring.coupon.service.CouponService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("coupon")
@RequiredArgsConstructor
class CouponRestController(val couponService: CouponService) {

    @PostMapping
    fun saveCoupon(@RequestBody couponSaveDto: CouponSaveDto): ResponseEntity<CommonResponse<CouponViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.COUPON_SAVE, couponService.saveCoupon(couponSaveDto)))
    }
}