package org.khw.shoppera.coupon.mapper


import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CouponMapper {


    fun entityToViewApiDto(coupon: Coupon): CouponViewApiDto

    fun entityListToViewApiDtoList(couponList: List<Coupon>): List<CouponViewApiDto>
}