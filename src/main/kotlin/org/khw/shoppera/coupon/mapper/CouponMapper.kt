package org.khw.shoppera.coupon.mapper


import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CouponMapper {

    @Mapping(target = "brandId", source = "brand.id")
    fun entityToViewApiDto(coupon: Coupon): CouponViewApiDto

    fun entityListToViewApiDtoList(couponList: List<Coupon>): List<CouponViewApiDto>
}