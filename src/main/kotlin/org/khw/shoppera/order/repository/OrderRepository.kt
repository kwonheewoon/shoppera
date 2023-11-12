package org.khw.shoppera.order.repository

import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.order.domain.entity.Order
import org.khw.shoppera.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface OrderRepository : JpaRepository <Order, Long>{

    /**
     * 주문 아이디(PK), 삭제 여부에 따른 주문 단일 조회
     *
     * @param id 주문 아이디(PK)
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<Order>

    /**
     * 주문 번호, 삭제 여부에 따른 주문 단일 조회
     *
     * @param id 주문 아이디(PK)
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByOrderNumberAndDeleteFlag(orderNumber: String, deleteFlag: FlagYn) : Optional<Order>

    /**
     * 주문 번호, User Entity, 삭제 여부에 따른 주문 단일 조회
     *
     * @param id 주문 아이디(PK)
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByOrderNumberAndUserAndDeleteFlag(orderNumber: String, user: User, deleteFlag: FlagYn) : Optional<Order>

    /**
     * User Entity, 삭제 여부에 따른 주문 단일 조회
     *
     * @param user User Entity
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByUserAndDeleteFlag(user: User, deleteFlag: FlagYn) : List<Order>

}