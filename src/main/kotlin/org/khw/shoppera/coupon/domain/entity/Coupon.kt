package org.khw.shoppera.coupon.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.common.enums.CommonEnum.*
import org.khw.shoppera.coupon.domain.dto.CouponUpdateDto
import java.time.LocalDate

@Entity
@Table(name = "coupon")
@Builder
@DynamicInsert
@DynamicUpdate
class Coupon(
    id: Long?,
    couponName: String,
    discountRate: Int,
    expireDate: LocalDate,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("쿠폰 아이디")
    val id: Long? = id

    @Column(name = "coupon_name", nullable = false)
    @Comment("쿠폰 이름")
    var couponName = couponName
        private set

    @Column(name = "discount_rate", nullable = false)
    @Comment("할인율")
    var discountRate = discountRate
        private set

    @Column(name = "expire_date", nullable = false)
    @Comment("만료 일자")
    var expireDate = expireDate
        private set

    @Column(name = "delete_flag", nullable = false)
    @Comment("삭제 여부")
    var deleteFlag = deleteFlag
        private set


    fun update(couponUpdateDto: CouponUpdateDto){
        this.couponName = couponUpdateDto.couponName
        this.discountRate = couponUpdateDto.discountRate
        this.expireDate = couponUpdateDto.expireDate
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }

}