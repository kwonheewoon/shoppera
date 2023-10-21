package org.khw.shoppera.usercoupon.mapper

import org.khw.shoppera.usercoupon.domain.dto.UserCouponViewApiDto
import org.khw.shoppera.usercoupon.domain.entity.UserCoupon
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserCouponMapper {

    @Mapping(target = "couponId", source = "coupon.id")
    @Mapping(target = "couponName", source = "coupon.couponName")
    @Mapping(target = "discountRate", source = "coupon.discountRate")
    @Mapping(target = "expireDate", source = "coupon.expireDate")
    fun entityToViewApiDto(userCoupon: UserCoupon): UserCouponViewApiDto

    fun entityListToViewApiDtoList(userCouponList: List<UserCoupon>) : List<UserCouponViewApiDto>
}