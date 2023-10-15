package org.khw.shoppera.coupon.mapper


import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.ItemEntity
import org.khw.shoppera.itemoption.mapper.ItemOptionMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface CouponMapper {


    fun entityToViewApiDto(coupon: Coupon): CouponViewApiDto

    fun entityListToViewApiDtoList(couponList: List<Coupon>): List<CouponViewApiDto>
}