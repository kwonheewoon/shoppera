package org.khw.kotlinspring.category.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.entity.QCategoryEntity.categoryEntity
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.springframework.stereotype.Repository


@Repository
@RequiredArgsConstructor
class CategoryQueryRepository(val queryFactory: JPAQueryFactory) {


    /**
     *
     */
    fun deleteCategory(categoryId: Long): Long {
        return queryFactory
            .update(categoryEntity)
            .set(categoryEntity.deleteFlag, FlagYn.Y)
            .where(categoryEntity.id.eq(categoryId))
            .execute()
    }
}