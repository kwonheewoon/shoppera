package org.khw.shoppera.usercoupon.controller

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.usercoupon.domain.dto.UserCouponSaveApiDto
import org.khw.shoppera.usercoupon.domain.dto.UserCouponViewApiDto
import org.khw.shoppera.usercoupon.service.UserCouponService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user-coupons")
@RequiredArgsConstructor
class UserCouponRestController(
    val userCouponService: UserCouponService
) {

    @PostMapping
    fun saveUserCoupon(@RequestBody userCouponSaveApiDto: UserCouponSaveApiDto): ResponseEntity<CommonResponse<UserCouponViewApiDto>> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.USER_COUPON_SAVE, userCouponService.saveUserCoupon(userCouponSaveApiDto)))
    }

    @PutMapping("/{userCouponId}/usage-status")
    fun saveUserCoupon(@PathVariable userCouponId: Long): ResponseEntity<CommonResponse<Unit>> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(CommonResponse(ResCode.USER_COUPON_USEAGE, userCouponService.useUserCoupon(userCouponId)))
    }
}