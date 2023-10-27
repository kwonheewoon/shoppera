package org.khw.shoppera.order.factory

import org.khw.shoppera.order.domain.dto.OrderDetailRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderDetailViewApiDto
import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import java.time.LocalDate

class CreateOrderDto {

    companion object{
        fun orderRequestApiDto(orderDetailList: List<OrderDetailRequestApiDto>): OrderRequestApiDto{
            return OrderRequestApiDto(1L, orderDetailList)
        }

        fun orderViewApiDto(orderDate: LocalDate, orderDetailList: List<OrderDetailViewApiDto>): OrderViewApiDto{
            return OrderViewApiDto("20231027234143774F46H", orderDate, orderDetailList)
        }
    }
}