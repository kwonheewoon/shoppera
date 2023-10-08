package org.khw.kotlinspring.coupon.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.coupon.domain.dto.CouponSaveDto
import org.khw.kotlinspring.coupon.domain.dto.CouponViewApiDto
import org.khw.kotlinspring.coupon.domain.entity.CouponEntityFactory
import org.khw.kotlinspring.coupon.mapper.CouponMapper
import org.khw.kotlinspring.coupon.repository.CouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class CouponService(val couponRepository: CouponRepository,
    val couponMapper: CouponMapper) {

    @Transactional
    fun saveCoupon(couponSaveDto: CouponSaveDto): CouponViewApiDto{
        val coupon = CouponEntityFactory.createCoupon(couponSaveDto)
        return couponMapper.entityToViewApiDto(couponRepository.save(coupon))
    }
}