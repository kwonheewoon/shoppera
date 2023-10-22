package org.khw.shoppera.usercoupon.repository

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.khw.shoppera.user.domain.entity.User
import org.khw.shoppera.usercoupon.domain.entity.UserCoupon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface UserCouponRepository : JpaRepository<UserCoupon, Long> {

    /**
     * userCouponId, 사용 여부, 삭제 여부, 쿠폰 만료일 기준 UserCoupon 단건 조회
     *
     * @param id 유저 쿠폰 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndUseFlagAndDeleteFlagAndCoupon_ExpireDateAfter(
        id: Long,
        useFlag: FlagYn,
        deleteFlag: FlagYn,
        expireDate: LocalDate
    ): Optional<UserCoupon>

    /**
     * User, Coupon, 삭제 여부 기준 UserCoupon 단건건 조회
     *
     * @param user UserEntity
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByUserAndCouponAndDeleteFlag(user: User, coupon: Coupon, deleteFlag: FlagYn) : Optional<UserCoupon>

    /**
     * User, 삭제 여부 기준 UserCoupon 다건 조회
     *
     * @param user UserEntity
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByUserAndDeleteFlag(user: User, deleteFlag: FlagYn) : List<UserCoupon>

}