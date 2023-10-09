package org.khw.kotlinspring.coupon.repository

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.coupon.domain.entity.Coupon
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CouponRepository : JpaRepository <Coupon, Long>{

    /**
     * 쿠폰 아이디, 삭제 여부에 따른 쿠폰 단일 조회
     *
     * @param id 쿠폰 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<Coupon>

    /**
     * 쿠폰 이름, 삭제 여부에 따른 쿠폰 단일 조회
     *
     * @param id 쿠폰 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByCouponNameAndDeleteFlag(couponName: String, deleteFlag: FlagYn) : Optional<Coupon>

    /**
     * 쿠폰 아이디, 쿠폰 이름, 삭제 여부에 따른 쿠폰 단일 조회
     *
     * @param id 쿠폰 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdNotAndCouponNameAndDeleteFlag(couponId: Long, couponName: String, deleteFlag: FlagYn) : Optional<Coupon>


}