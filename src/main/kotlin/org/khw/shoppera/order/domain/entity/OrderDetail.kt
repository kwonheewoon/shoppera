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
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.user.domain.entity.User
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "order_details")
@Builder
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
class OrderDetail(
    id: Long? = null,
    order: Order? = null,
    item: Item,
    price: Int,
    quantity: Int,
    state: OrderState,
    orderDateTime: LocalDateTime,
    deleteFlag: FlagYn = FlagYn.N
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @Comment("아이템 아이디")
    var item = item
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

    fun setOrder(order: Order){
        this.order = order
    }

    fun updateState(nowState: OrderState){
        when(nowState){
            OrderState.PAYMENT_CONFIRM -> paymentConfirm()
            OrderState.SHIPMENT_REQUEST -> TODO()
            OrderState.SHIPMENT_PROCESS -> TODO()
            OrderState.SHIPMENT_COMPLETED -> TODO()
            OrderState.ORDER_REQUEST -> TODO()
        }
    }

    private fun paymentConfirm(){
        require(this.state == OrderState.ORDER_REQUEST) { throw OrderException(ResCode.ORDER_STATE_NOT_REQUEST) }

        this.state = OrderState.PAYMENT_CONFIRM
    }
}