package org.khw.shoppera.item.repository

import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemRepository : JpaRepository <Item, Long>{

    /**
     * 삭제 여부에 따른 아이템 단일 조회
     *
     * @param id 아이템 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<Item>

    /**
     * 삭제 여부에 따른 아이템 다건 조회
     *
     * @param ids 아이템 아이디 리스트
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdInAndDeleteFlag(ids: List<Long>, deleteFlag: FlagYn) : List<Item>

    /**
     * 카테고리, 삭제 여부에 따른 아이템 다건 조회
     *
     * @param category 카테고리 정보
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByCategoryAndDeleteFlag(category: Category, deleteFlag: FlagYn) : List<Item>

}