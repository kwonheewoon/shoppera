package org.khw.shoppera.category.repository

import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CategoryRepository : JpaRepository <Category, Long>{

    /**
     * 삭제 여부, depth 1을 가진 카테고리 전체조회
     *
     * @param id 카테고리 아이디
     * @param deleteFlag 삭제여부
     * @return List<CategoryEntity>
     */
    fun findByDepthAndDeleteFlag(depth: Int, deleteFlag: FlagYn) : List<Category>

    /**
     * 삭제 여부에 따른 카테고리 단일 조회
     *
     * @param id 카테고리 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: FlagYn) : Optional<Category>

    fun findByParentCategoryIdAndDepth(parentId: Long, depth: Int)
}