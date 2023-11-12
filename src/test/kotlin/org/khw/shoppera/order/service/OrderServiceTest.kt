package org.khw.shoppera.order.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.brand.factory.CreateBrandEntity
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.category.factory.CreateCategoryEntity
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.OrderException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.common.factory.user.CreateUserEntity
import org.khw.shoppera.common.util.JwtUtil
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.item.domain.entity.ItemType
import org.khw.shoppera.item.factory.CreateItemDto
import org.khw.shoppera.item.factory.CreateItemEntity
import org.khw.shoppera.item.factory.CreateItemTypeEntity
import org.khw.shoppera.item.repository.ItemRepository
import org.khw.shoppera.order.domain.dto.*
import org.khw.shoppera.order.domain.entity.Order
import org.khw.shoppera.order.domain.entity.OrderDetail
import org.khw.shoppera.order.domain.mapper.OrderMapper
import org.khw.shoppera.order.factory.CreateOrder
import org.khw.shoppera.order.factory.CreateOrderDetail
import org.khw.shoppera.order.factory.CreateOrderDetailDto
import org.khw.shoppera.order.factory.CreateOrderDto
import org.khw.shoppera.order.repository.OrderRepository
import org.khw.shoppera.user.domain.entity.User
import org.khw.shoppera.user.repository.UserRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

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

    @Mock
    lateinit var orderNotiService: OrderNotiService

    @InjectMocks
    lateinit var orderService: OrderService

    lateinit var nowDateTime: LocalDateTime
    lateinit var orderNumber: String
    lateinit var orderRequestApiDto: OrderRequestApiDto
    lateinit var orderDetailApiDtoList: List<OrderDetailRequestApiDto>
    lateinit var findUser: User
    lateinit var findBrand: Brand
    lateinit var findCategory: Category
    lateinit var findItemType: ItemType
    lateinit var findItemList: List<Item>
    lateinit var findOrder: Order
    lateinit var findOrderDetailList: List<OrderDetail>
    lateinit var orderViewApiDto: OrderViewApiDto
    lateinit var orderDetailViewApiDtoList: List<OrderDetailViewApiDto>

    @BeforeEach
    fun setUp() {
        nowDateTime = LocalDateTime.of(LocalDate.of(2023, 11, 1), LocalTime.of(23,0))
        orderNumber = "20231027234143774F46H"

        orderDetailApiDtoList = CreateOrderDetailDto.orderRequestApiDtoList()
        orderRequestApiDto = CreateOrderDto.orderRequestApiDto(orderDetailApiDtoList)
        findUser = CreateUserEntity.findSuccessCreate()
        findBrand = CreateBrandEntity.findBrand()
        findCategory = CreateCategoryEntity.categoryEntityParent()
        findItemType = CreateItemTypeEntity.findItemTypeEntity()
        findItemList = CreateItemEntity.findItemEntityList(findBrand, findCategory, findItemType)
        findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        findOrderDetailList = CreateOrderDetail.findRequestOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)
        orderDetailViewApiDtoList = CreateOrderDetailDto.orderDetailViewApiDtoList(
            CreateItemDto.itemViewApiDtoList(), OrderState.ORDER_REQUEST, nowDateTime
        )
        orderViewApiDto = CreateOrderDto.orderViewApiDto(nowDateTime.toLocalDate(), orderDetailViewApiDtoList)

    }

    @Test
    fun `accountId 별 주문 전체 조회 실패(존재하지 않는 유저)`(){
        // Given
        val accountId = "gmldns46"
        val authentication = mock(Authentication::class.java)
        val securityContext = mock(SecurityContext::class.java)

        `when`(securityContext.authentication).thenReturn(authentication)
        `when`(authentication.name).thenReturn(accountId)

        SecurityContextHolder.setContext(securityContext)

        mockStatic(JwtUtil::class.java).use {
            given(JwtUtil.getName()).willReturn(accountId)
            given(userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N))
                .willReturn(Optional.empty())
        }

        // When
        val throwable = assertThrows(UserException::class.java){
            orderService.findAllMyOrders()
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_USER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_USER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_USER.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `accountId 별 주문 전체 조회 성공`(){
        // Given
        val accountId = "gmldns46"
        val authentication = mock(Authentication::class.java)
        val securityContext = mock(SecurityContext::class.java)
        val findOrderList = CreateOrder.findOrderList(findUser)
        findOrderList.forEach {
            it.orderDetailAdd(findOrderDetailList)
        }
        val orderViewApiDtoList = CreateOrderDto.orderViewApiDtoList(orderDetailViewApiDtoList)


        `when`(securityContext.authentication).thenReturn(authentication)
        `when`(authentication.name).thenReturn(accountId)

        SecurityContextHolder.setContext(securityContext)

        mockStatic(JwtUtil::class.java).use {
            given(JwtUtil.getName()).willReturn(accountId)
            given(userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N))
                .willReturn(Optional.of(findUser))
            given(orderRepository.findByUserAndDeleteFlag(findUser, FlagYn.N))
                .willReturn(findOrderList)
            given(orderMapper.entityListToViewApiDtoList(findOrderList))
                .willReturn(orderViewApiDtoList)
        }

        // When
        val result = orderService.findAllMyOrders()

        // Then
        assertEquals(result, orderViewApiDtoList)
        assertEquals(result.size, 3)
        assertEquals(result[0].orderNumber, "20231027234143774F46H")
        assertEquals(result[0].orderDate, LocalDate.of(2023, 10, 27))
        verify(userRepository).findByAccountIdAndDeleteFlag(accountId, FlagYn.N)
        verify(orderRepository).findByUserAndDeleteFlag(findUser, FlagYn.N)
        verify(orderMapper).entityListToViewApiDtoList(findOrderList)
    }

    @Test
    fun `주문 요청 실패(존재하지 않는 유저)`(){
        // Given
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

    @Test
    fun `주문 요청 실패(아이템 정보 조회 실패)`(){
        // Given
        val itemIds = orderRequestApiDto.orderDetailList.map { it.itemId }

        given(userRepository.findByIdAndDeleteFlag(orderRequestApiDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUser))
        given(itemRepository.findByIdInAndDeleteFlag(itemIds, FlagYn.N))
            .willReturn(emptyList())

        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderRequest(orderRequestApiDto)
        }

        // Then
        assertEquals(ResCode.ORDER_REQUEST_FAIL.code, throwable.code)
        assertEquals(ResCode.ORDER_REQUEST_FAIL.message, throwable.message)
        assertEquals(ResCode.ORDER_REQUEST_FAIL.httpStatus, HttpStatus.OK)

    }

    @Test
    fun `주문 요청 성공`(){
        // Given
        val itemIds = orderRequestApiDto.orderDetailList.map { it.itemId }
        val saveOrder = CreateOrder.saveOrder(orderRequestApiDto, "20231027234143774F46H", findUser, nowDateTime.toLocalDate())
        val saveOrderDetailList = CreateOrderDetail.orderDetailRequestList(orderDetailApiDtoList, findItemList.associateBy { it.id!! },
            nowDateTime)
        val savedOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val savedOrderDetailList = CreateOrderDetail.findRequestOrderDetailList(orderDetailApiDtoList, savedOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        savedOrder.orderDetailAdd(savedOrderDetailList)

        given(userRepository.findByIdAndDeleteFlag(orderRequestApiDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUser))
        given(itemRepository.findByIdInAndDeleteFlag(itemIds, FlagYn.N))
            .willReturn(findItemList)
        saveOrder.orderDetailAdd(saveOrderDetailList)
        given(orderRepository.save(any(Order::class.java)))
            .willReturn(savedOrder)
        given(orderMapper.entityToViewApiDto(savedOrder))
            .willReturn(orderViewApiDto)

        // When
        val result = orderService.orderRequest(orderRequestApiDto)

        // Then
        assertEquals(result, orderViewApiDto)

        assertEquals(result.orderDate, nowDateTime.toLocalDate())
        assertEquals(result.orderNumber, "20231027234143774F46H")
        assertEquals(result.orderDetailList.size, 3)

        assertEquals(result.orderDetailList[0].item.id, 1L)
        assertEquals(result.orderDetailList[0].item.itemName, "하와이안 셔츠")
        assertEquals(result.orderDetailList[0].orderDateTime, nowDateTime)
        assertEquals(result.orderDetailList[0].price, 65000)
        assertEquals(result.orderDetailList[0].quantity, 3)

        verify(userRepository).findByIdAndDeleteFlag(orderRequestApiDto.userId, FlagYn.N)
        verify(itemRepository).findByIdInAndDeleteFlag(itemIds, FlagYn.N)
        verify(orderRepository).save(any(Order::class.java))
        verify(orderMapper).entityToViewApiDto(savedOrder)

    }

    @Test
    fun `주문 결제 확인 상태변경 실패(존재하지 않는 주문 정보)`(){
        // Given
        val orderNumber = "20231027234143774F46H"

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderPaymentConfirm(orderNumber)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ORDER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ORDER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ORDER.httpStatus, HttpStatus.NOT_FOUND)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 결제 확인 상태변경 실패(주문 요청 상태가 아닌 주문)`(){
        // Given

        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findPaymentConfirmOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderPaymentConfirm(orderNumber)
        }

        // Then
        assertEquals(ResCode.ORDER_STATE_NOT_REQUEST.code, throwable.code)
        assertEquals(ResCode.ORDER_STATE_NOT_REQUEST.message, throwable.message)
        assertEquals(ResCode.ORDER_STATE_NOT_REQUEST.httpStatus, HttpStatus.OK)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 결제 확인 상태변경 성공`(){
        // Given
        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        orderService.orderPaymentConfirm(orderNumber)

        // Then
        findOrderDetailList.forEach {
            assertEquals(it.state, OrderState.PAYMENT_CONFIRM)
        }
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 요청 상태변경 실패(존재하지 않는 주문 정보)`(){
        // Given
        val orderNumber = "20231027234143774F46H"

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderShipmentRequest(orderNumber)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ORDER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ORDER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ORDER.httpStatus, HttpStatus.NOT_FOUND)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 요청 상태변경 실패(주문 결제 확인 상태가 아닌 주문)`(){
        // Given
        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findShipmentRequestOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderShipmentRequest(orderNumber)
        }

        // Then
        assertEquals(ResCode.ORDER_STATE_NOT_PAYMENT_CONFIRM.code, throwable.code)
        assertEquals(ResCode.ORDER_STATE_NOT_PAYMENT_CONFIRM.message, throwable.message)
        assertEquals(ResCode.ORDER_STATE_NOT_PAYMENT_CONFIRM.httpStatus, HttpStatus.OK)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 요청 상태변경 상태변경 성공`(){
        // Given
        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findPaymentConfirmOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)
        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        orderService.orderShipmentRequest(orderNumber)

        // Then
        findOrderDetailList.forEach {
            assertEquals(it.state, OrderState.SHIPMENT_REQUEST)
        }
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 처리 상태변경 실패(존재하지 않는 주문 정보)`(){
        // Given
        val orderNumber = "20231027234143774F46H"

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderShipmentProcess(orderNumber)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ORDER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ORDER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ORDER.httpStatus, HttpStatus.NOT_FOUND)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 처리 상태변경 실패(주문 출고 요청 상태가 아닌 주문)`(){
        // Given
        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findShipmentProcessOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderShipmentProcess(orderNumber)
        }

        // Then
        assertEquals(ResCode.ORDER_STATE_NOT_SHIPMENT_REQUEST.code, throwable.code)
        assertEquals(ResCode.ORDER_STATE_NOT_SHIPMENT_REQUEST.message, throwable.message)
        assertEquals(ResCode.ORDER_STATE_NOT_SHIPMENT_REQUEST.httpStatus, HttpStatus.OK)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 처리 상태변경 상태변경 성공`(){
        // Given
        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findShipmentRequestOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)
        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        orderService.orderShipmentProcess(orderNumber)

        // Then
        findOrderDetailList.forEach {
            assertEquals(it.state, OrderState.SHIPMENT_PROCESS)
        }
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 완료 상태변경 실패(존재하지 않는 주문 정보)`(){
        // Given
        val orderNumber = "20231027234143774F46H"

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderShipmentCompleted(orderNumber)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ORDER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ORDER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ORDER.httpStatus, HttpStatus.NOT_FOUND)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 완료 상태변경 실패(주문 출고 처리 상태가 아닌 주문)`(){
        // Given
        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findShipmentRequestOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))


        // When
        val throwable = assertThrows(OrderException::class.java){
            orderService.orderShipmentCompleted(orderNumber)
        }

        // Then
        assertEquals(ResCode.ORDER_STATE_NOT_SHIPMENT_PROCESS.code, throwable.code)
        assertEquals(ResCode.ORDER_STATE_NOT_SHIPMENT_PROCESS.message, throwable.message)
        assertEquals(ResCode.ORDER_STATE_NOT_SHIPMENT_PROCESS.httpStatus, HttpStatus.OK)
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)

    }

    @Test
    fun `주문 출고 완료 상태변경 상태변경 성공`(){
        // Given
        val findOrder = CreateOrder.findOrder(findUser, nowDateTime.toLocalDate())
        val findOrderDetailList = CreateOrderDetail.findShipmentProcessOrderDetailList(orderDetailApiDtoList, findOrder, findItemList.associateBy { it.id!! }, nowDateTime)
        findOrder.orderDetailAdd(findOrderDetailList)
        val orderShipCompNotiDto = CreateOrderDto.orderShipCompNotiDto(nowDateTime.toLocalDate(), CreateOrderDetailDto.orderDetailDtoList(CreateItemDto.itemViewApiDtoList(), OrderState.ORDER_REQUEST, nowDateTime))

        given(orderRepository.findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N))
            .willReturn(Optional.of(findOrder))
        given(orderMapper.entityToOrderShipCompNotiDto(findOrder))
            .willReturn(orderShipCompNotiDto)


        // When
        orderService.orderShipmentCompleted(orderNumber)

        // Then
        findOrderDetailList.forEach {
            assertEquals(it.state, OrderState.SHIPMENT_COMPLETED)
        }
        verify(orderRepository).findByOrderNumberAndDeleteFlag(orderNumber, FlagYn.N)
        verify(orderMapper).entityToOrderShipCompNotiDto(findOrder)
        verify(orderNotiService).produceOrderShipCompNoti(orderShipCompNotiDto)
    }


}