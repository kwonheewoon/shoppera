package org.khw.shoppera.ordernotification.service

import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.khw.shoppera.order.domain.dto.OrderShipCompNotiDto
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class OrderNotificationService(
) {
    private val log = KotlinLogging.logger {}

    /**
     * Queue에서 메시지를 구독
     *
     * @param orderShipCompNotiDto 구독한 메시지를 담고 있는 OrderShipCompNotiDto 객체
     */
    @RabbitListener(queues = ["\${rabbitmq.realtime.order.updates.queue}"])
    fun reciveMessage(orderShipCompNotiDto: OrderShipCompNotiDto) {
        log.info("Received message: {}", orderShipCompNotiDto);
    }
}