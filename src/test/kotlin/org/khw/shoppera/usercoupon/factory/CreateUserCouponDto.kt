package org.khw.shoppera.usercoupon.factory

import org.khw.shoppera.usercoupon.domain.dto.UserCouponSaveApiDto
import org.khw.shoppera.usercoupon.domain.dto.UserCouponViewApiDto
import org.khw.shoppera.usercoupon.domain.entity.UserCoupon
import java.time.LocalDate

class CreateUserCouponDto {
    companion object{
        fun userCouponSaveApiDto(): UserCouponSaveApiDto {
            return UserCouponSaveApiDto(1, 20)
        }

        fun userCouponViewApiDto(userCoupon: UserCoupon): UserCouponViewApiDto{
            return UserCouponViewApiDto(1L, userCoupon.coupon.id!!, userCoupon.coupon.couponName, userCoupon.coupon.discountRate, userCoupon.coupon.expireDate)
        }

        fun userCouponViewApiDtoList(): List<UserCouponViewApiDto>{
            return listOf(
                UserCouponViewApiDto(1L, 20, "쿠폰1", 35, LocalDate.of(2023, 12, 1)),
                UserCouponViewApiDto(2L, 21, "쿠폰2", 25, LocalDate.of(2023, 12, 2)),
                UserCouponViewApiDto(3L, 22, "쿠폰3", 40, LocalDate.of(2023, 12, 3))
            )
        }
    }
}