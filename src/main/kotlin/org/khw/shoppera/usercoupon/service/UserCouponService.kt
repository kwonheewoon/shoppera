package org.khw.shoppera.usercoupon.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.CouponException
import org.khw.shoppera.common.exception.UserCouponException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.coupon.repository.CouponRepository
import org.khw.shoppera.user.repository.UserRepository
import org.khw.shoppera.usercoupon.domain.dto.UserCouponSaveApiDto
import org.khw.shoppera.usercoupon.domain.dto.UserCouponViewApiDto
import org.khw.shoppera.usercoupon.domain.entity.UserCoupon
import org.khw.shoppera.usercoupon.mapper.UserCouponMapper
import org.khw.shoppera.usercoupon.repository.UserCouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class UserCouponService(
    val userCouponRepository: UserCouponRepository,
    val userRepository: UserRepository,
    val couponRepository: CouponRepository,
    val userCouponMapper: UserCouponMapper
) {

    @Transactional
    fun saveUserCoupon(userCouponSaveApiDto: UserCouponSaveApiDto): UserCouponViewApiDto{
        val findUser = userRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.userId, FlagYn.N).orElseThrow { throw UserException(ResCode.NOT_FOUND_USER) }
        val findCoupon = couponRepository.findByIdAndDeleteFlag(userCouponSaveApiDto.couponId, FlagYn.N).orElseThrow { throw CouponException(ResCode.NOT_FOUND_COUPON) }

        userCouponRepository.findByUserAndCouponAndDeleteFlag(findUser, findCoupon, FlagYn.N).ifPresent { throw UserCouponException(ResCode.DUPLICATE_USER_COUPON) }

        return userCouponMapper.entityToViewApiDto(
            userCouponRepository.save(UserCoupon( user = findUser, coupon =  findCoupon))
        )
    }

    @Transactional
    fun useUserCoupon(userCouponId: Long){
        val findUserCoupon = userCouponRepository.findByIdAndUseFlagAndDeleteFlagAndCoupon_ExpireDateAfter(userCouponId, FlagYn.N, FlagYn.N, LocalDate.now()).orElseThrow { throw UserCouponException(ResCode.NOT_FOUND_USER_COUPON) }

        findUserCoupon.useProcess()
    }

}