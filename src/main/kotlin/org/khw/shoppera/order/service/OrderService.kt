package org.khw.shoppera.order.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.common.util.RandomUtils
import org.khw.shoppera.item.repository.ItemRepository
import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
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
    val orderNotiService: OrderNotiService,
    val orderRepository: OrderRepository,
    val userRepository: UserRepository,
    val itemRepository: ItemRepository,
    val orderMapper: OrderMapper
) {

    @Transactional
    fun orderRequest(orderRequestApiDto: OrderRequestApiDto): OrderViewApiDto{
        val findUser = userRepository.findByIdAndDeleteFlag(orderRequestApiDto.userId, FlagYn.N).orElseThrow { throw UserException(ResCode.NOT_FOUND_USER) }

        val itemIds = orderRequestApiDto.orderDetailList.map { it.itemId }
        val itemList = itemRepository.findByIdInAndDeleteFlag(itemIds, FlagYn.N)

        if(itemList.isEmpty() || itemList.size != itemIds.size){
            throw OrderException(ResCode.ORDER_REQUEST_FAIL)
        }

        val nowDateTime = LocalDateTime.now()

        val order = OrderFactory.createOrder(orderRequestApiDto, RandomUtils.generateOrderNumber(), findUser, nowDateTime.toLocalDate())
        order.orderDetailAdd(
            OrderDetailFactory.createOrderDetailList(
                orderRequestApiDto.orderDetailList,
                itemList.associateBy { it.id!! },
                OrderState.ORDER_REQUEST,
                nowDateTime
            )
        )

        return orderMapper.entityToViewApiDto(
            orderRepository.save(order)
        )
    }

    @Transactional
    fun orderPaymentConfirm(orderNumber: String){
        val findOrder = orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N).orElseThrow { throw OrderException(ResCode.NOT_FOUND_ORDER) }

        findOrder.paymentConfirm()
    }

    @Transactional
    fun orderShipmentRequest(orderNumber: String){
        val findOrder = orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N).orElseThrow { throw OrderException(ResCode.NOT_FOUND_ORDER) }

        findOrder.shipmentRequest()
    }

    @Transactional
    fun orderShipmentProcess(orderNumber: String){
        val findOrder = orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N).orElseThrow { throw OrderException(ResCode.NOT_FOUND_ORDER) }

        findOrder.shipmentProcess()
    }

    @Transactional
    fun orderShipmentCompleted(orderNumber: String){
        val findOrder = orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N).orElseThrow { throw OrderException(ResCode.NOT_FOUND_ORDER) }

        findOrder.shipmentCompleted()

        orderNotiService.produceOrderShipCompNoti(orderMapper.entityToOrderShipCompNotiDto(findOrder))
    }

}