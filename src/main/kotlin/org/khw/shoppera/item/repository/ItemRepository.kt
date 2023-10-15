package org.khw.shoppera.item.repository

import org.khw.shoppera.category.domain.entity.CategoryEntity
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemRepository : JpaRepository <ItemEntity, Long>{

    /**
     * 삭제 여부에 따른 아이템 단일 조회
     *
     * @param id 아이템 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<ItemEntity>

    /**
     * 카테고리, 삭제 여부에 따른 아이템 다건 조회
     *
     * @param categoryEntity 카테고리 정보
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByCategoryAndDeleteFlag(categoryEntity: CategoryEntity, deleteFlag: FlagYn) : List<ItemEntity>

}