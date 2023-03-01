package org.khw.kotlinspring.category.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.entity.QCategoryEntity
import org.khw.kotlinspring.category.domain.entity.QCategoryEntity.categoryEntity
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


@Repository
@RequiredArgsConstructor
class CategoryQueryRepository(val queryFactory: JPAQueryFactory) {


    /**
     *
     */
    fun deleteCategory(categoryId: Long): Long {
        return queryFactory
            .update(categoryEntity)
            .set(categoryEntity.deleteFlag, "Y")
            .where(categoryEntity.id.eq(categoryId))
            .execute()
    }
}