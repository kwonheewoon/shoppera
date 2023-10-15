package org.khw.shoppera.coupon.domain.entity

import org.khw.shoppera.coupon.domain.dto.CouponSaveDto

class CouponEntityFactory {
    companion object{
        fun createCoupon(couponSaveDto: CouponSaveDto): Coupon{
            return Coupon(null, couponSaveDto.couponName, couponSaveDto.discountRate, couponSaveDto.expireDate)
        }
    }
}