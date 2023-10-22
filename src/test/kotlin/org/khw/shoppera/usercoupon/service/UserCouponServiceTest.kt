package org.khw.shoppera.usercoupon.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.khw.shoppera.common.exception.CouponException
import org.khw.shoppera.common.exception.UserCouponException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.common.factory.coupon.CreateCouponEntity
import org.khw.shoppera.common.factory.user.CreateUserEntity
import org.khw.shoppera.coupon.repository.CouponRepository
import org.khw.shoppera.user.repository.UserRepository
import org.khw.shoppera.usercoupon.domain.entity.UserCoupon
import org.khw.shoppera.usercoupon.factory.CreateUserCouponDto
import org.khw.shoppera.usercoupon.factory.CreateUserCouponEntity
import org.khw.shoppera.usercoupon.mapper.UserCouponMapper
import org.khw.shoppera.usercoupon.repository.UserCouponRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.http.HttpStatus
import java.time.LocalDate
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

    @Test
    fun `유저 쿠폰 등록 실패(이미 발급 받은 쿠폰)`(){
        val userCouponSaveApiDto = CreateUserCouponDto.userCouponSaveApiDto()
        val findUser = CreateUserEntity.findSuccessCreate()
        val findCoupon = CreateCouponEntity.findCouponEntity()
        val findUserCoupon = CreateUserCouponEntity.findUserCoupon()

        given(userRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUser))
        given(couponRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.couponId, FlagYn.N))
            .willReturn(Optional.of(findCoupon))
        given(userCouponRepository.findByUserAndCouponAndDeleteFlag(findUser, findCoupon, FlagYn.N))
            .willReturn(Optional.of(findUserCoupon))

        // When
        val throwable = assertThrows(UserCouponException::class.java){
            userCouponService.saveUserCoupon(userCouponSaveApiDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_USER_COUPON.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_USER_COUPON.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_USER_COUPON.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `유저 쿠폰 등록 성공`(){
        val userCouponSaveApiDto = CreateUserCouponDto.userCouponSaveApiDto()
        val findUser = CreateUserEntity.findSuccessCreate()
        val findCoupon = CreateCouponEntity.findCouponEntity()
        val savedUserCoupon = CreateUserCouponEntity.savedUserCoupon(userCouponSaveApiDto)
        val userCouponViewApiDto = CreateUserCouponDto.userCouponViewApiDto(savedUserCoupon)

        given(userRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUser))
        given(couponRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.couponId, FlagYn.N))
            .willReturn(Optional.of(findCoupon))
        given(userCouponRepository.findByUserAndCouponAndDeleteFlag(findUser, findCoupon, FlagYn.N))
            .willReturn(Optional.empty())
        given(userCouponRepository.save(any(UserCoupon::class.java)))
            .willReturn(savedUserCoupon)
        given(userCouponMapper.entityToViewApiDto(savedUserCoupon))
            .willReturn(userCouponViewApiDto)

        // When

        val result = userCouponService.saveUserCoupon(userCouponSaveApiDto)


        // Then
        assertEquals(result, userCouponViewApiDto)
        assertEquals(result.couponId, userCouponViewApiDto.couponId)
        assertEquals(result.userCouponId, userCouponViewApiDto.userCouponId)

        verify(userRepository).findByIdAndDeleteFlag(userCouponSaveApiDto.userId, FlagYn.N)
        verify(couponRepository).findByIdAndDeleteFlag(userCouponSaveApiDto.couponId, FlagYn.N)
        verify(userCouponRepository).findByUserAndCouponAndDeleteFlag(findUser, findCoupon, FlagYn.N)
        verify(userCouponRepository).save(any(UserCoupon::class.java))
        verify(userCouponMapper).entityToViewApiDto(savedUserCoupon)
    }

    @Test
    fun `유저 쿠폰 사용 실패(존재하지 않는 쿠폰)`(){
        val userCouponId = 20L

        given(userCouponRepository.findByIdAndUseFlagAndDeleteFlagAndCoupon_ExpireDateAfter(userCouponId, FlagYn.N, FlagYn.N, LocalDate.of(2023,10,22)))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(UserCouponException::class.java){
            userCouponService.useUserCoupon(userCouponId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_USER_COUPON.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_USER_COUPON.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_USER_COUPON.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `유저 쿠폰 사용 성공`(){
        val userCouponId = 20L
        val findUserCoupon = CreateUserCouponEntity.findUserCoupon()

        given(userCouponRepository.findByIdAndUseFlagAndDeleteFlagAndCoupon_ExpireDateAfter(userCouponId, FlagYn.N, FlagYn.N, LocalDate.of(2023,10,22)))
            .willReturn(Optional.of(findUserCoupon))

        // When
        userCouponService.useUserCoupon(userCouponId)

        // Then
        assertEquals(findUserCoupon.useFlag, FlagYn.Y)
        verify(userCouponRepository).findByIdAndUseFlagAndDeleteFlagAndCoupon_ExpireDateAfter(userCouponId, FlagYn.N, FlagYn.N, LocalDate.of(2023,10,22))
    }
}