package org.khw.shoppera.coupon.domain.entity

import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.coupon.domain.dto.CouponSaveApiDto

class CouponFactory {
    companion object{
        fun createCoupon(couponSaveApiDto: CouponSaveApiDto, brand: Brand): Coupon{
            return Coupon(null, couponSaveApiDto.couponName, couponSaveApiDto.discountRate, couponSaveApiDto.expireDate, brand)
        }
    }
}