package org.khw.shoppera.order.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.item.repository.ItemRepository
import org.khw.shoppera.order.domain.mapper.OrderMapper
import org.khw.shoppera.order.factory.CreateOrder
import org.khw.shoppera.order.factory.CreateOrderDetail
import org.khw.shoppera.order.factory.CreateOrderDetailDto
import org.khw.shoppera.order.factory.CreateOrderDto
import org.khw.shoppera.order.repository.OrderRepository
import org.khw.shoppera.user.repository.UserRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ThreadLocalRandom

@ExtendWith(MockitoExtension::class)
class OrderServiceTest {

    @Mock
    lateinit var orderRepository: OrderRepository

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var itemRepository: ItemRepository

    @Mock
    lateinit var orderMapper: OrderMapper

    @InjectMocks
    lateinit var orderService: OrderService

    @Test
    fun `주문 요청 실패(존재하지 않는 유저)`(){
        // Given
        val orderDetailApiDtoList = CreateOrderDetailDto.orderRequestApiDtoList()
        val orderRequestApiDto = CreateOrderDto.orderRequestApiDto(orderDetailApiDtoList)

        given(userRepository.findByIdAndDeleteFlag(orderRequestApiDto.userId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java){
            orderService.orderRequest(orderRequestApiDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_USER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_USER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_USER.httpStatus, HttpStatus.NOT_FOUND)

    }


}