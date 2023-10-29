package org.khw.shoppera.order.factory

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.order.domain.dto.OrderDetailRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderDetailViewApiDto
import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import java.time.LocalDate
import java.time.LocalDateTime

class CreateOrderDetailDto {

    companion object{
        fun orderRequestApiDto(): OrderDetailRequestApiDto{
            return OrderDetailRequestApiDto(1L, 65000, 3)
        }

        fun orderRequestApiDtoList(): List<OrderDetailRequestApiDto>{
            return listOf(
                OrderDetailRequestApiDto(1L, 65000, 3),
                OrderDetailRequestApiDto(2L, 45000, 4),
                OrderDetailRequestApiDto(3L, 25000, 6)
            )
        }

        fun orderDetailViewApiDto(item: ItemViewApiDto, state: OrderState, orderDateTime: LocalDateTime): OrderDetailViewApiDto{
            return OrderDetailViewApiDto(item, 65000, 3, state.name, orderDateTime)
        }

        fun orderDetailViewApiDtoList(items: List<ItemViewApiDto>, state: OrderState, orderDateTime: LocalDateTime): List<OrderDetailViewApiDto>{
            return listOf(
                OrderDetailViewApiDto(items[0], 65000, 3, state.name, orderDateTime),
                OrderDetailViewApiDto(items[1], 45000, 4, state.name, orderDateTime),
                OrderDetailViewApiDto(items[2], 25000, 6, state.name, orderDateTime)
            )
        }
    }
}