package org.khw.shoppera.order.domain.mapper


import org.khw.shoppera.order.domain.dto.OrderShipCompNotiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.domain.entity.Order
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [OrderDetailMapper::class])
interface OrderMapper {

    @Mapping(target = "orderDetailList", source = "orderDetailList")
    fun entityToOrderShipCompNotiDto(order: Order): OrderShipCompNotiDto

    @Mapping(target = "orderDetailList", source = "orderDetailList")
    fun entityToViewApiDto(order: Order): OrderViewApiDto

    fun entityListToViewApiDtoList(orderList: List<Order>): List<OrderViewApiDto>
}