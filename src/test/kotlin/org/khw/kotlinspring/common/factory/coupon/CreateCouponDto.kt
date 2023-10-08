package org.khw.kotlinspring.common.factory.coupon

import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.coupon.domain.dto.CouponSaveDto
import org.khw.kotlinspring.coupon.domain.dto.CouponViewApiDto
import java.time.LocalDate

class CreateCouponDto {

    companion object{
        fun couponSaveDto(): CouponSaveDto{
            return CouponSaveDto("모든 품목 할인율 15%!!", 15, LocalDate.of(2023, 10, 8))
        }

        fun couponViewApiDto(): CouponViewApiDto{
            return CouponViewApiDto(1, "모든 품목 할인율 15%!!", 15, LocalDate.of(2023, 10, 8), FlagYn.N)
        }
    }
}