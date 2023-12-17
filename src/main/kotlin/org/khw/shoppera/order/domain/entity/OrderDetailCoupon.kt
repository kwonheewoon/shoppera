package org.khw.shoppera.order.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.common.entity.BaseEntity
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.coupon.domain.entity.Coupon
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.user.domain.entity.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "order_detail_coupons")
@Builder
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
class OrderDetailCoupon(
    id: Long? = null,
    orderDetail: OrderDetail? = null,
    coupon: Coupon
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("주문 아이디 아이디")
    val id: Long? = id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    @Comment("주문 상세 아이디")
    var orderDetail = orderDetail
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    @Comment("쿠폰 아이디")
    var coupon = coupon
        private set

    @Embedded
    val baseEntity = BaseEntity()
}