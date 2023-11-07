package org.khw.shoppera.order.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.order.domain.dto.OrderShipCompNotiDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class OrderNotiService(
    @Value("\${rabbitmq.order.notifications.exchange}") val orderNotificationsExchange: String,
    @Value("\${rabbitmq.order.notifications.routing.key}") val orderNotificationsRoutingKey: String,
    val rabbitTemplate: RabbitTemplate
) {

    fun produceOrderShipCompNoti(orderShipCompNotiDto: OrderShipCompNotiDto){
        rabbitTemplate.convertAndSend(orderNotificationsExchange, orderNotificationsRoutingKey, orderShipCompNotiDto);
    }
}