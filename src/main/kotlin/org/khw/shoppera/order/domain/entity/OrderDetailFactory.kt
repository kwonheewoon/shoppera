package org.khw.shoppera.order.domain.entity

import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.order.domain.dto.OrderDetailRequestApiDto
import java.time.LocalDateTime

class OrderDetailFactory {
    companion object{
        fun createOrderDetail(orderDetailRequestApiDto: OrderDetailRequestApiDto, item: Item, state: OrderState, orderDateTime: LocalDateTime): OrderDetail{
            return OrderDetail(item = item, price = orderDetailRequestApiDto.price, quantity =  orderDetailRequestApiDto.quantity, state = state, orderDateTime = orderDateTime)
        }

        fun createOrderDetailList(orderDetailRequestApiDtoList: List<OrderDetailRequestApiDto>, itemMap: Map<Long, Item>, state: OrderState, orderDateTime: LocalDateTime): List<OrderDetail>{
            return orderDetailRequestApiDtoList.map {
                val item = itemMap[it.itemId] ?: throw OrderException(ResCode.ORDER_REQUEST_FAIL)
                createOrderDetail(it, item, state, orderDateTime)
            }
        }
    }
}