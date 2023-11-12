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

        fun findOrderList(user: User): List<Order>{
            return listOf(
                Order(1L, orderNumber = "20231027234143774F46H", user = user, orderDate = LocalDate.of(2023, 10, 27)),
                Order(2L, orderNumber = "20231028113043774A25L", user = user, orderDate = LocalDate.of(2023, 10, 28)),
                Order(3L, orderNumber = "20231029192745484QF5G", user = user, orderDate = LocalDate.of(2023, 10, 29))
            )
        }
    }
}