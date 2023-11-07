package org.khw.shoppera.config.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.rabbitmq.client.BlockedListener
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.amqp.core.ReturnedMessage
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException


@Configuration
@RequiredArgsConstructor
@Slf4j
class RabbitMQConfig(
    @Value("\${spring.rabbitmq.host}") val rabbitmqHost: String,

    @Value("\${spring.rabbitmq.port}") val rabbitmqPort: Int,

    @Value("\${spring.rabbitmq.username}") val rabbitmqUsername: String,

    @Value("\${spring.rabbitmq.password}") val rabbitmqPassword: String,
) {

    private val log = KotlinLogging.logger {}

    /**
     * RabbitMQ 연결을 위한 ConnectionFactory 빈을 생성하여 반환
     *
     * @return ConnectionFactory 객체
     */
    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.host = rabbitmqHost
        connectionFactory.port = rabbitmqPort
        connectionFactory.username = rabbitmqUsername
        connectionFactory.setPassword(rabbitmqPassword)

        // 정상 라우팅 되지 않은 메시지 발행자가 받기 활성화
        connectionFactory.setPublisherReturns(true)

        // 발행자 확인 활성화(트랜잭션 모드와 겸용 불가)
        //connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory
    }

    @Bean
    fun createConnection(): Connection {
        val connection = connectionFactory().createConnection()
        connection.addBlockedListener(object : BlockedListener {
            @Throws(IOException::class)
            override fun handleBlocked(reason: String) {
                println("블락 되지 않았다 시바거")
            }

            @Throws(IOException::class)
            override fun handleUnblocked() {
                println("블락 되지 않았다 시바거")
            }
        })
        return connection
    }

    @Bean
    fun rabbitAdmin(): RabbitAdmin {
        return RabbitAdmin(connectionFactory())
    }

    /**
     * RabbitTemplate을 생성하여 반환
     *
     * @param connectionFactory RabbitMQ와의 연결을 위한 ConnectionFactory 객체
     * @return RabbitTemplate 객체
     */
    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory?): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory!!)
        // JSON 형식의 메시지를 직렬화하고 역직렬할 수 있도록 설정
        rabbitTemplate.messageConverter = jackson2JsonMessageConverter()
        rabbitTemplate.setMandatory(true)
        rabbitTemplate.setReturnsCallback { returned -> log.info("Message returned: {}", returned)
        }

        //트랜잭션 모드 활성화
        rabbitTemplate.isChannelTransacted = true
        rabbitTemplate.setConfirmCallback { correlationData: CorrelationData?, ack: Boolean, cause: String? ->
            if (ack) {
                log.info("Message confirmed: correlation : {}, ack : {}, cause : {}", correlationData, ack, cause);
            } else {
                log.warn("Message not confirmed: {}", cause);
            }
        }
        return rabbitTemplate
    }

    /**
     * Kotlin Jackson 라이브러리를 사용하여 메시지를 JSON 형식으로 변환하는 MessageConverter 빈을 생성
     *
     * @return MessageConverter 객체
     */
    @Bean
    fun jackson2JsonMessageConverter(): MessageConverter {
        val objectMapper = jacksonObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        return Jackson2JsonMessageConverter(objectMapper)
    }
}
