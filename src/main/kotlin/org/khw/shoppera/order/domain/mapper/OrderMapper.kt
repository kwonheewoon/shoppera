package org.khw.shoppera.order.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.mapper.ItemOptionMapper
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.domain.entity.Order
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [OrderDetailMapper::class])
interface OrderMapper {

    @Mapping(target = "orderDetailList", source = "orderDetailList")
    fun entityToViewApiDto(order: Order): OrderViewApiDto

    fun entityListToViewApiDtoList(orderList: List<Order>): List<OrderViewApiDto>
}