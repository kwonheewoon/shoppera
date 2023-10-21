package org.khw.shoppera.usercoupon.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.khw.shoppera.user.domain.entity.User

@Entity
@Table(name = "users_coupon")
@Builder
@DynamicInsert
@DynamicUpdate
class UserCoupon(
    id: Long? = null,
    user: User,
    coupon: Coupon,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("아이템 아이디")
    val id: Long? = id

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Comment("유저 아이디(pk)")
    var user = user
        private set

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coupon_id")
    @Comment("쿠폰 아이디")
    var coupon = coupon
        private set

    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

}