package org.khw.shoppera.order.domain.entity

import org.khw.shoppera.order.domain.dto.OrderSaveApiDto
import org.khw.shoppera.user.domain.entity.User
import java.time.LocalDate
import java.time.LocalDateTime

class OrderFactory {
    companion object{
        fun createOrder(orderSaveApiDto: OrderSaveApiDto, orderNumber: String, user: User, orderDate: LocalDate): Order{
            return Order(orderNumber = orderNumber, user = user, orderDate = orderDate)
        }
    }
}