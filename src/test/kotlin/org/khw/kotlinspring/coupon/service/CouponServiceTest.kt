package org.khw.kotlinspring.coupon.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.factory.coupon.CreateCouponDto
import org.khw.kotlinspring.common.factory.coupon.CreateCouponEntity
import org.khw.kotlinspring.coupon.domain.entity.Coupon
import org.khw.kotlinspring.coupon.mapper.CouponMapper
import org.khw.kotlinspring.coupon.repository.CouponRepository

import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CouponServiceTest {

    @Mock
    lateinit var couponMapper: CouponMapper

    @Mock
    lateinit var couponRepository: CouponRepository

    @InjectMocks
    lateinit var couponService: CouponService

    @Test
    fun `쿠폰 등록 실패`(){
        // Given
        val couponSaveDto = CreateCouponDto.couponSaveDto()
        val couponViewApiDto = CreateCouponDto.couponViewApiDto()
        val couponSavedEntity = CreateCouponEntity.saveCouponEntity()

        given(couponRepository.save(any(Coupon::class.java)))
            .willReturn(couponSavedEntity)
        given(couponMapper.entityToViewApiDto(couponSavedEntity))
            .willReturn(couponViewApiDto)


        // When
        val result = couponService.saveCoupon(couponSaveDto)

        // Then
        assertEquals(result, couponViewApiDto)
        assertEquals(result.couponName, couponViewApiDto.couponName)
        verify(couponRepository).save(any(Coupon::class.java))
        verify(couponMapper).entityToViewApiDto(couponSavedEntity)
    }
}