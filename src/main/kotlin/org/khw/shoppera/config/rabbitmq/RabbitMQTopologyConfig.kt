package org.khw.shoppera.config.rabbitmq

import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@RequiredArgsConstructor
class RabbitMQTopologyConfig(
    @Value("\${rabbitmq.order.notifications.exchange}") val orderNotificationsExchange: String,
    @Value("\${rabbitmq.realtime.order.updates.queue}") val realtimeOrderUpdatesQueue: String,
    @Value("\${rabbitmq.order.notifications.routing.key}") val orderNotificationsRoutingKey: String,
    val rabbitAdmin: RabbitAdmin
) {

    private val log = KotlinLogging.logger {}


    /**
     * 지정된 익스체인지 이름으로 DirectExchange 빈을 생성
     *
     * @return DirectExchange 빈 객체
     */
    @Bean
    fun exchange(): DirectExchange {
        val directExchange = DirectExchange(orderNotificationsExchange)
        directExchange.isInternal = false
        rabbitAdmin.declareExchange(directExchange)
        return directExchange
    }

    /**
     * Queue 생성 realtime-order-updates-queue
     *
     * @return Queue 빈 객체
     */
    @Bean
    fun queue(): Queue {
        val args: MutableMap<String, Any> = HashMap()

        // exclusive : 독점 큐(채널이 닫히면 큐가 삭제됨)
        // autoDelete : 자독삭제 임시 큐(해당 큐에 구독하고 있는 소비자가 존재하지 않을시 큐가 삭제됨)
        val queue = Queue(realtimeOrderUpdatesQueue, true, false, false, args)
        rabbitAdmin.declareQueue(queue)
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange()).with(orderNotificationsRoutingKey))
        return queue
    }
}