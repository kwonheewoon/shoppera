package org.khw.shoppera.coupon.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.CouponException
import org.khw.shoppera.common.factory.coupon.CreateCouponDto
import org.khw.shoppera.common.factory.coupon.CreateCouponEntity
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.khw.shoppera.coupon.mapper.CouponMapper
import org.khw.shoppera.coupon.repository.CouponRepository

import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
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
        val findCoupon = CreateCouponEntity.findCouponEntity()

        given(couponRepository.findByCouponNameAndDeleteFlag(couponSaveDto.couponName, FlagYn.N))
            .willReturn(Optional.of(findCoupon))


        // When
        val throwable = assertThrows(CouponException::class.java){
            couponService.saveCoupon(couponSaveDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `쿠폰 등록 성공`(){
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

    @Test
    fun `쿠폰 수정 실패`(){
        // Given
        val couponId = 1L
        val couponUpdateDto = CreateCouponDto.couponUpdateDto()
        val findDupCoupon = CreateCouponEntity.findCouponEntity()

        given(couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateDto.couponName, FlagYn.N))
            .willReturn(Optional.of(findDupCoupon))


        // When
        val throwable = assertThrows(CouponException::class.java){
            couponService.updateCoupon(couponId, couponUpdateDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `쿠폰 수정 성공`(){
        // Given
        val couponId = 1L
        val couponUpdateDto = CreateCouponDto.couponUpdateDto()
        val couponViewApiDto = CreateCouponDto.couponViewApiDto(couponUpdateDto)
        val findCouponEntity = CreateCouponEntity.findCouponEntity()

        given(couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateDto. couponName, FlagYn.N))
            .willReturn(Optional.empty())
        given(couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N))
            .willReturn(Optional.of(findCouponEntity))
        given(couponMapper.entityToViewApiDto(findCouponEntity))
            .willReturn(couponViewApiDto)


        // When
        val result = couponService.updateCoupon(couponId, couponUpdateDto)

        // Then
        assertEquals(result, couponViewApiDto)
        assertEquals(result.couponName, couponViewApiDto.couponName)
        assertEquals(result.couponName, "모든 품목 할인율 35%!!")
        assertEquals(result.discountRate, 35)
        verify(couponRepository).findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateDto. couponName, FlagYn.N)
        verify(couponRepository).findByIdAndDeleteFlag(couponId, FlagYn.N)
        verify(couponMapper).entityToViewApiDto(findCouponEntity)
    }

    @Test
    fun `쿠폰 삭제 성공`(){
        // Given
        val couponId = 1L
        val findCouponEntity = CreateCouponEntity.findCouponEntity()

        given(couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N))
            .willReturn(Optional.of(findCouponEntity))

        // When
        couponService.deleteCoupon(couponId)

        // Then
        verify(couponRepository).findByIdAndDeleteFlag(couponId, FlagYn.N)
    }
}