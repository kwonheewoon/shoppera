package org.khw.shoppera.order.domain.entity

import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.user.domain.entity.User
import java.time.LocalDate

class OrderFactory {
    companion object{
        fun createOrder(orderRequestApiDto: OrderRequestApiDto, orderNumber: String, user: User, orderDate: LocalDate): Order{
            return Order(orderNumber = orderNumber, user = user, orderDate = orderDate)
        }
    }
}