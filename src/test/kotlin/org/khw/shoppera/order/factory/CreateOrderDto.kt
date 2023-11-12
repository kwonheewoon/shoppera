package org.khw.shoppera.order.factory

import org.khw.shoppera.order.domain.dto.*
import java.time.LocalDate

class CreateOrderDto {

    companion object{
        fun orderRequestApiDto(orderDetailList: List<OrderDetailRequestApiDto>): OrderRequestApiDto{
            return OrderRequestApiDto(1L, orderDetailList)
        }

        fun orderViewApiDto(orderDate: LocalDate, orderDetailList: List<OrderDetailViewApiDto>): OrderViewApiDto{
            return OrderViewApiDto("20231027234143774F46H", orderDate, orderDetailList)
        }

        fun orderViewApiDtoList(orderDetailList: List<OrderDetailViewApiDto>): List<OrderViewApiDto>{
            return listOf(
                OrderViewApiDto("20231027234143774F46H", LocalDate.of(2023, 10, 27), orderDetailList),
                OrderViewApiDto("20231028113043774A25L", LocalDate.of(2023, 10, 28), orderDetailList),
                OrderViewApiDto("20231029192745484QF5G", LocalDate.of(2023, 10, 29), orderDetailList)
            )
        }

        fun orderShipCompNotiDto(orderDate: LocalDate, orderDetailList: List<OrderDetailDto>): OrderShipCompNotiDto {
            return OrderShipCompNotiDto("20231027234143774F46H", orderDate, orderDetailList)
        }
    }
}