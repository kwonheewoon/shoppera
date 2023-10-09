package org.khw.kotlinspring.coupon.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.CouponException
import org.khw.kotlinspring.coupon.domain.dto.CouponSaveDto
import org.khw.kotlinspring.coupon.domain.dto.CouponUpdateDto
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

        couponRepository.findByCouponNameAndDeleteFlag(couponSaveDto.couponName, FlagYn.N).ifPresent { throw CouponException(ResCode.DUPLICATE_COUPON_NAME) }

        val coupon = CouponEntityFactory.createCoupon(couponSaveDto)
        return couponMapper.entityToViewApiDto(couponRepository.save(coupon))
    }

    @Transactional
    fun updateCoupon(couponId: Long, couponUpdateDto: CouponUpdateDto): CouponViewApiDto{

        couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateDto.couponName, FlagYn.N).ifPresent { throw CouponException(ResCode.DUPLICATE_COUPON_NAME) }

        val findCoupon = couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N).orElseThrow { throw CouponException(ResCode.NOT_FOUND_COUPON) }

        findCoupon.update(couponUpdateDto)

        return couponMapper.entityToViewApiDto(findCoupon)
    }

    @Transactional
    fun deleteCoupon(couponId: Long) {
        val findCoupon = couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N).orElseThrow { throw CouponException(ResCode.NOT_FOUND_COUPON) }

        findCoupon.delete()
    }
}