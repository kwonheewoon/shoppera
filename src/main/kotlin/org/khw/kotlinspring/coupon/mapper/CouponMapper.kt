package org.khw.kotlinspring.coupon.mapper


import org.khw.kotlinspring.coupon.domain.dto.CouponViewApiDto
import org.khw.kotlinspring.coupon.domain.entity.Coupon
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.khw.kotlinspring.itemoption.mapper.ItemOptionMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [ItemOptionMapper::class])
interface CouponMapper {


    fun entityToViewApiDto(coupon: Coupon): CouponViewApiDto

    fun entityListToViewApiDtoList(couponList: List<Coupon>): List<CouponViewApiDto>
}