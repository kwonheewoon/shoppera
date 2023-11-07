package org.khw.shoppera.order.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.item.domain.mapper.ItemMapper
import org.khw.shoppera.itemoption.mapper.ItemOptionMapper
import org.khw.shoppera.order.domain.dto.OrderDetailDto
import org.khw.shoppera.order.domain.dto.OrderDetailViewApiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.domain.entity.OrderDetail
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [ItemMapper::class])
interface OrderDetailMapper {

    @Mapping(target = "item", source = "item")
    fun entityToOrderDetailDto(orderDetail: OrderDetail): OrderDetailDto

    @Mapping(target = "item", source = "item")
    fun entityToViewApiDto(orderDetail: OrderDetail): OrderDetailViewApiDto

    fun entityListToViewApiDtoList(orderDetailList: List<OrderDetail>): List<OrderDetailViewApiDto>
}