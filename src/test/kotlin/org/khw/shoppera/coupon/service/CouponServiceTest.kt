package org.khw.shoppera.coupon.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.brand.factory.CreateBrandEntity
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
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

    @Mock
    lateinit var brandRepository: BrandRepository

    @InjectMocks
    lateinit var couponService: CouponService

    @Test
    fun `쿠폰 등록 실패(쿠폰 이름 중복)`(){
        // Given
        val couponSaveDto = CreateCouponDto.couponSaveApiDto()
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
    fun `쿠폰 등록 실패(브랜드 정보 없음)`(){
        // Given
        val couponSaveApiDto = CreateCouponDto.couponSaveApiDto()

        given(couponRepository.findByCouponNameAndDeleteFlag(couponSaveApiDto.couponName, FlagYn.N))
            .willReturn(Optional.empty())
        given(brandRepository.findByIdAndDeleteFlag(couponSaveApiDto.brandId, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(BrandException::class.java){
            couponService.saveCoupon(couponSaveApiDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_BRAND.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_BRAND.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_BRAND.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `쿠폰 등록 성공`(){
        // Given
        val couponSaveApiDto = CreateCouponDto.couponSaveApiDto()
        val couponViewApiDto = CreateCouponDto.couponViewApiDto()
        val findBrand = CreateBrandEntity.findBrand()
        val savedCoupon = CreateCouponEntity.saveCouponEntity(findBrand)

        given(couponRepository.findByCouponNameAndDeleteFlag(couponSaveApiDto.couponName, FlagYn.N))
            .willReturn(Optional.empty())
        given(brandRepository.findByIdAndDeleteFlag(couponSaveApiDto.brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(couponRepository.save(any(Coupon::class.java)))
            .willReturn(savedCoupon)
        given(couponMapper.entityToViewApiDto(savedCoupon))
            .willReturn(couponViewApiDto)


        // When
        val result = couponService.saveCoupon(couponSaveApiDto)

        // Then
        assertEquals(result, couponViewApiDto)
        assertEquals(result.couponName, couponViewApiDto.couponName)
        verify(couponRepository).save(any(Coupon::class.java))
        verify(couponMapper).entityToViewApiDto(savedCoupon)
    }

    @Test
    fun `쿠폰 수정 실패(중복된 쿠폰 이름)`(){
        // Given
        val couponId = 1L
        val couponUpdateApiDto = CreateCouponDto.couponUpdateApiDto()
        val findDupCoupon = CreateCouponEntity.findCouponEntity()

        given(couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateApiDto.couponName, FlagYn.N))
            .willReturn(Optional.of(findDupCoupon))


        // When
        val throwable = assertThrows(CouponException::class.java){
            couponService.updateCoupon(couponId, couponUpdateApiDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_COUPON_NAME.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `쿠폰 수정 실패(존재하지 않는 브랜드)`(){
        // Given
        val couponId = 1L
        val couponUpdateApiDto = CreateCouponDto.couponUpdateApiDto()

        given(couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateApiDto.couponName, FlagYn.N))
            .willReturn(Optional.empty())
        given(brandRepository.findByIdAndDeleteFlag(couponUpdateApiDto.brandId, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(BrandException::class.java){
            couponService.updateCoupon(couponId, couponUpdateApiDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_BRAND.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_BRAND.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_BRAND.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `쿠폰 수정 성공`(){
        // Given
        val couponId = 1L
        val couponUpdateApiDto = CreateCouponDto.couponUpdateApiDto()
        val couponViewApiDto = CreateCouponDto.couponViewApiDto(couponUpdateApiDto)
        val findCouponEntity = CreateCouponEntity.findCouponEntity()
        val findBrand = CreateBrandEntity.findBrand()

        given(couponRepository.findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateApiDto. couponName, FlagYn.N))
            .willReturn(Optional.empty())
        given(brandRepository.findByIdAndDeleteFlag(couponUpdateApiDto.brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(couponRepository.findByIdAndDeleteFlag(couponId, FlagYn.N))
            .willReturn(Optional.of(findCouponEntity))
        given(couponMapper.entityToViewApiDto(findCouponEntity))
            .willReturn(couponViewApiDto)


        // When
        val result = couponService.updateCoupon(couponId, couponUpdateApiDto)

        // Then
        assertEquals(result, couponViewApiDto)
        assertEquals(result.couponName, couponViewApiDto.couponName)
        assertEquals(result.couponName, "모든 품목 할인율 35%!!")
        assertEquals(result.discountRate, 35)
        assertEquals(result.brandId, 2)
        verify(couponRepository).findByIdNotAndCouponNameAndDeleteFlag(couponId, couponUpdateApiDto. couponName, FlagYn.N)
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