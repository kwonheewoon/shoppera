package org.khw.shoppera.order.factory

import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.order.domain.dto.OrderDetailRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.order.domain.entity.Order
import org.khw.shoppera.order.domain.entity.OrderDetail
import org.khw.shoppera.order.domain.entity.OrderDetailFactory
import org.khw.shoppera.user.domain.entity.User
import java.time.LocalDate
import java.time.LocalDateTime

class CreateOrderDetail {
    companion object{
        fun orderDetailRequestList(orderDetailRequestApiDtoList: List<OrderDetailRequestApiDto>, itemMap: Map<Long, Item>, orderDateTime: LocalDateTime): List<OrderDetail>{
            return orderDetailRequestApiDtoList.map {
                orderDetail(it, itemMap[it.itemId]!!, OrderState.ORDER_REQUEST, orderDateTime)
            }
        }

        fun orderDetail(orderDetailRequestApiDto: OrderDetailRequestApiDto, item: Item, state: OrderState, orderDateTime: LocalDateTime): OrderDetail{
            return OrderDetail(item = item, price = orderDetailRequestApiDto.price, quantity =  orderDetailRequestApiDto.quantity, state = state, orderDateTime = orderDateTime)
        }
    }
}