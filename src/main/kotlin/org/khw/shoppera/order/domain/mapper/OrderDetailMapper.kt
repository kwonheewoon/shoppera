package org.khw.shoppera.order.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.mapper.ItemOptionMapper
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.domain.entity.OrderDetail
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface OrderDetailMapper {


    fun entityToViewApiDto(orderDetail: OrderDetail): OrderViewApiDto

    fun entityListToViewApiDtoList(orderDetailList: List<OrderDetail>): List<OrderViewApiDto>
}