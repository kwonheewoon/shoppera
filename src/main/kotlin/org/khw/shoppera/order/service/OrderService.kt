package org.khw.shoppera.order.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.common.util.RandomUtils
import org.khw.shoppera.item.repository.ItemRepository
import org.khw.shoppera.order.domain.dto.OrderSaveApiDto
import org.khw.shoppera.order.domain.dto.OrderViewApiDto
import org.khw.shoppera.order.domain.entity.OrderDetailFactory
import org.khw.shoppera.order.domain.entity.OrderFactory
import org.khw.shoppera.order.domain.mapper.OrderMapper
import org.khw.shoppera.order.repository.OrderRepository
import org.khw.shoppera.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@RequiredArgsConstructor
class OrderService(
    val orderRepository: OrderRepository,
    val userRepository: UserRepository,
    val itemRepository: ItemRepository,
    val orderMapper: OrderMapper
) {

    @Transactional
    fun saveOrder(orderSaveApiDto: OrderSaveApiDto): OrderViewApiDto{
        val findUser = userRepository.findByIdAndDeleteFlag(orderSaveApiDto.userId, FlagYn.N).orElseThrow { throw UserException(ResCode.NOT_FOUND_USER) }

        val itemIds = orderSaveApiDto.orderDetailList.map { it.itemId }
        val itemList = itemRepository.findByIdInAndDeleteFlag(itemIds, FlagYn.N)

        if(itemList.size != itemIds.size){
            throw OrderException(ResCode.ORDER_SAVE_FAIL)
        }

        val nowDateTime = LocalDateTime.now()

        val saveOrder = OrderFactory.createOrder(orderSaveApiDto, RandomUtils.generateOrderNumber(), findUser, nowDateTime.toLocalDate(),
            OrderDetailFactory.createOrderDetailList(orderSaveApiDto.orderDetailList,
                itemList.associateBy { it.id!! },
                nowDateTime))

        return orderMapper.entityToViewApiDto(
            orderRepository.save(saveOrder)
        )
    }

}