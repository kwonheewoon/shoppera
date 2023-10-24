package org.khw.shoppera.order.domain.entity

import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.order.domain.dto.OrderDetailSaveApiDto
import java.time.LocalDateTime

class OrderDetailFactory {
    companion object{
        fun createOrderDetail(orderDetailSaveApiDto: OrderDetailSaveApiDto, item: Item, orderDateTime: LocalDateTime): OrderDetail{
            return OrderDetail(item = item, price = orderDetailSaveApiDto.price, quantity =  orderDetailSaveApiDto.quantity, state = OrderState.fromString(orderDetailSaveApiDto.state), orderDateTime = orderDateTime)
        }

        fun createOrderDetailList(orderDetailSaveApiDtoList: List<OrderDetailSaveApiDto>, itemMap: Map<Long, Item>, orderDateTime: LocalDateTime): List<OrderDetail>{
            return orderDetailSaveApiDtoList.map {
                val item = itemMap[it.itemId] ?: throw OrderException(ResCode.ORDER_SAVE_FAIL)
                createOrderDetail(it, item, orderDateTime)
            }
        }
    }
}