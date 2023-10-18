package org.khw.shoppera.common.factory.coupon

import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.brand.factory.CreateBrandEntity
import org.khw.shoppera.coupon.domain.entity.Coupon
import java.time.LocalDate

class CreateCouponEntity {

    companion object{
        fun saveCouponEntity(brand: Brand): Coupon{
            return Coupon(1L, "모든 품목 할인율 15%!!", 15, LocalDate.of(2023, 10, 8), brand)
        }
        fun findCouponEntity(): Coupon{
            return Coupon(1L, "모든 품목 할인율 15%!!", 15, LocalDate.of(2023, 10, 8), CreateBrandEntity.findBrand())
        }
    }
}