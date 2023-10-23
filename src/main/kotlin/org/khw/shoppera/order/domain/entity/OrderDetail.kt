package org.khw.shoppera.order.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.common.entity.BaseEntity
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.user.domain.entity.User
import java.time.LocalDateTime

@Entity
@Table(name = "order_details")
@Builder
@DynamicInsert
@DynamicUpdate
class OrderDetail(
    id: Long? = null,
    order: Order,
    price: Int,
    quantity: Int,
    state: OrderState,
    orderDateTime: LocalDateTime,
    deleteFlag: CommonEnum.FlagYn = CommonEnum.FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("주문 아이디 아이디")
    val id: Long? = id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Comment("주문 아이디")
    var order = order
        private set

    @Column(name = "price", nullable = false)
    @Comment("주문 가격")
    var price = price
        private set

    @Column(name = "quantity", nullable = false)
    @Comment("주문 수량")
    var quantity = quantity
        private set

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("주문 상태")
    var state = state
        private set

    @Column(name = "order_date_time", nullable = false)
    @Comment("주문 시각")
    var orderDateTime = orderDateTime
        private set

    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    @Embedded
    val baseEntity = BaseEntity()
}