package org.khw.shoppera.usercoupon.factory

import org.khw.shoppera.usercoupon.domain.dto.UserCouponSaveApiDto
import org.khw.shoppera.usercoupon.domain.dto.UserCouponViewApiDto
import java.time.LocalDate

class CreateUserCouponDto {
    companion object{
        fun userCouponSaveApiDto(): UserCouponSaveApiDto {
            return UserCouponSaveApiDto(1, 20)
        }

        fun userCouponViewApiDtoList(): List<UserCouponViewApiDto>{
            return listOf(
                UserCouponViewApiDto(1, 20, "쿠폰1", 35, LocalDate.of(2023, 12, 1)),
                UserCouponViewApiDto(2, 21, "쿠폰2", 25, LocalDate.of(2023, 12, 2)),
                UserCouponViewApiDto(3, 22, "쿠폰3", 40, LocalDate.of(2023, 12, 3))
            )
        }
    }
}