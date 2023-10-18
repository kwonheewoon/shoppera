package org.khw.shoppera.common.factory.coupon

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.coupon.domain.dto.CouponSaveApiDto
import org.khw.shoppera.coupon.domain.dto.CouponUpdateApiDto
import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import java.time.LocalDate

class CreateCouponDto {

    companion object{
        fun couponSaveApiDto(): CouponSaveApiDto{
            return CouponSaveApiDto(1L, "모든 품목 할인율 15%!!", 15, LocalDate.of(2023, 10, 8))
        }

        fun couponUpdateApiDto(): CouponUpdateApiDto{
            return CouponUpdateApiDto(2L,"모든 품목 할인율 35%!!", 35, LocalDate.of(2023, 10, 20))
        }

        fun couponViewApiDto(): CouponViewApiDto{
            return CouponViewApiDto(1L, 1L, "모든 품목 할인율 15%!!", 15, LocalDate.of(2023, 10, 8), FlagYn.N)
        }

        fun couponViewApiDto(couponUpdateApiDto: CouponUpdateApiDto): CouponViewApiDto{
            return CouponViewApiDto(1L, couponUpdateApiDto.brandId, couponUpdateApiDto.couponName, couponUpdateApiDto.discountRate, couponUpdateApiDto.expireDate, FlagYn.N)
        }
    }
}