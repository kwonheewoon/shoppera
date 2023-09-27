package org.khw.kotlinspring.coupon.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.common.enums.CommonEnum.*
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
    isUsed: FlagYn = FlagYn.N,
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

    @Column(name = "is_used", nullable = false)
    @Comment("사용 여부")
    var isUsed = isUsed
        private set

    @Column(name = "delete_flag", nullable = false)
    @Comment("삭제 여부")
    var deleteFlag = deleteFlag
        private set

}