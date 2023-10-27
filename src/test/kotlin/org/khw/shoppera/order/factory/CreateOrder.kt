package org.khw.shoppera.order.factory

import org.khw.shoppera.order.domain.dto.OrderRequestApiDto
import org.khw.shoppera.order.domain.entity.Order
import org.khw.shoppera.user.domain.entity.User
import java.time.LocalDate

class CreateOrder {
    companion object{
        fun saveOrder(orderRequestApiDto: OrderRequestApiDto, orderNumber: String, user: User, orderDate: LocalDate): Order {
            return Order(orderNumber = orderNumber, user = user, orderDate = orderDate)
        }

        fun findOrder(user: User, orderDate: LocalDate): Order{
            return Order(1L, orderNumber = "20231027234143774F46H", user = user, orderDate = orderDate)
        }
    }
}