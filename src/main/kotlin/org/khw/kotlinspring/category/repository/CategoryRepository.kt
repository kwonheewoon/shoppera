package org.khw.kotlinspring.category.repository

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.CommonEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CategoryRepository : JpaRepository <CategoryEntity, Long>{

    /**
     * 삭제 여부, depth 1을 가진 카테고리 전체조회
     *
     * @param id 카테고리 아이디
     * @param deleteFlag 삭제여부
     * @return List<CategoryEntity>
     */
    fun findByDepthAndDeleteFlag(depth: Int, deleteFlag: CommonEnum.FlagYn) : List<CategoryEntity>

    /**
     * 삭제 여부에 따른 카테고리 단일 조회
     *
     * @param id 카테고리 아이디
     * @param deleteFlag 삭제여부
     * @return
     */
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: String) : Optional<CategoryEntity>

    fun findByParentCategoryIdAndDepth(parentId: Long, depth: Int)
}