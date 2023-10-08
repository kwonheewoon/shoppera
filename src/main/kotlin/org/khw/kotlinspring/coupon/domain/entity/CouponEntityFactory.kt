package org.khw.kotlinspring.coupon.domain.entity

import org.khw.kotlinspring.coupon.domain.dto.CouponSaveDto

class CouponEntityFactory {
    companion object{
        fun createCoupon(couponSaveDto: CouponSaveDto): Coupon{
            return Coupon(null, couponSaveDto.couponName, couponSaveDto.discountRate, couponSaveDto.expireDate)
        }
    }
}