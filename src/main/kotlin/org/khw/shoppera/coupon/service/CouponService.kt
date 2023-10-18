package org.khw.shoppera.coupon.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.khw.shoppera.common.exception.CouponException
import org.khw.shoppera.coupon.domain.dto.CouponSaveApiDto
import org.khw.shoppera.coupon.domain.dto.CouponUpdateApiDto
import org.khw.shoppera.coupon.domain.dto.CouponViewApiDto
import org.khw.shoppera.coupon.domain.entity.CouponFactory
import org.khw.shoppera.coupon.mapper.CouponMapper
import org.khw.shoppera.coupon.repository.CouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class CouponService(
    val brandRepository: BrandRepository,
    val couponRepository: CouponRepository,
    val couponMapper: CouponMapper) {

    @Transactional
    fun saveCoupon(couponSaveApiDto: CouponSaveApiDto): CouponViewApiDto{

        couponRepository.findByCouponNameAndDeleteFlag(couponSaveApiDto.couponName, FlagYn.N).ifPresent { throw CouponException(ResCode.DUPLICATE_COUPON_NAME) }

        val findBrand = brandRepository.findByIdAndDeleteFlag(couponSaveApiDto.brandId, FlagYn.N).orElseThrow { throw BrandException(ResCode.NOT_FOUND_BRAND) }

        val coupon = CouponFactory.createCoupon(couponSaveApiDto, findBrand)
        return couponMapper.entityToViewApiDto(couponRepository.save(coupon))
    }

    @Transactional
    fun updateCoupon(couponId: Long, couponUpdateApiDto: CouponUpdateApiDto): CouponViewApiDto{

        couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateApiDto.couponName, FlagYn.N).ifPresent { throw CouponException(ResCode.DUPLICATE_COUPON_NAME) }

        val findBrand = brandRepository.findByIdAndDeleteFlag(couponUpdateApiDto.brandId, FlagYn.N).orElseThrow { throw BrandException(ResCode.NOT_FOUND_BRAND) }

        val findCoupon = couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N).orElseThrow { throw CouponException(ResCode.NOT_FOUND_COUPON) }

        findCoupon.update(couponUpdateApiDto, findBrand)

        return couponMapper.entityToViewApiDto(findCoupon)
    }

    @Transactional
    fun deleteCoupon(couponId: Long) {
        val findCoupon = couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N).orElseThrow { throw CouponException(ResCode.NOT_FOUND_COUPON) }

        findCoupon.delete()
    }
}