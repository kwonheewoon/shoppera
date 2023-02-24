package org.khw.kotlinspring.category.repository

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository <CategoryEntity, Long>{
    fun findByIdAndDeleteFlag(id: Long, deleteFlag: String)
}