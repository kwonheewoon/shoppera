package org.khw.shoppera.usercoupon.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.khw.shoppera.common.exception.CouponException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.common.factory.user.CreateUserEntity
import org.khw.shoppera.coupon.repository.CouponRepository
import org.khw.shoppera.user.repository.UserRepository
import org.khw.shoppera.usercoupon.factory.CreateUserCouponDto
import org.khw.shoppera.usercoupon.mapper.UserCouponMapper
import org.khw.shoppera.usercoupon.repository.UserCouponRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserCouponServiceTest {

    @Mock
    lateinit var userCouponRepository: UserCouponRepository

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var couponRepository: CouponRepository

    @Mock
    lateinit var userCouponMapper: UserCouponMapper

    @InjectMocks
    lateinit var userCouponService: UserCouponService

    @Test
    fun `유저 쿠폰 등록 실패(존재 하지 않는 유저)`(){
        val userCouponSaveApiDto = CreateUserCouponDto.userCouponSaveApiDto()

        given(userRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.userId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java){
            userCouponService.saveUserCoupon(userCouponSaveApiDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_USER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_USER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_USER.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `유저 쿠폰 등록 실패(존재 하지 않는 쿠폰)`(){
        val userCouponSaveApiDto = CreateUserCouponDto.userCouponSaveApiDto()
        val findUser = CreateUserEntity.findSuccessCreate()

        given(userRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUser))
        given(couponRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.couponId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(CouponException::class.java){
            userCouponService.saveUserCoupon(userCouponSaveApiDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_COUPON.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_COUPON.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_COUPON.httpStatus, HttpStatus.NOT_FOUND)
    }
}