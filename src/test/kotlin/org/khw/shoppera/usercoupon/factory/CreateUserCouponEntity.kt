package org.khw.shoppera.usercoupon.factory

import org.khw.shoppera.common.factory.coupon.CreateCouponEntity
import org.khw.shoppera.common.factory.user.CreateUserEntity
import org.khw.shoppera.usercoupon.domain.dto.UserCouponSaveApiDto
import org.khw.shoppera.usercoupon.domain.dto.UserCouponViewApiDto
import org.khw.shoppera.usercoupon.domain.entity.UserCoupon
import java.time.LocalDate

class CreateUserCouponEntity {

    companion object{
        fun savedUserCoupon(userCouponSaveApiDto: UserCouponSaveApiDto): UserCoupon{
            return UserCoupon(1L, CreateUserEntity.findSuccessCreate(), CreateCouponEntity.findCouponEntity())
        }

        fun findUserCoupon(): UserCoupon{
            return UserCoupon(1L, CreateUserEntity.findSuccessCreate(), CreateCouponEntity.findCouponEntity())
        }
    }
}