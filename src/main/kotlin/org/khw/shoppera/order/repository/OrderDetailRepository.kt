package org.khw.shoppera.order.repository

import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.order.domain.entity.OrderDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface OrderDetailRepository : JpaRepository <OrderDetail, Long>{

    /**
     * 삭제 여부에 따른 아이템 단일 조회
     *
     * @param id 아이템 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<OrderDetail>

}