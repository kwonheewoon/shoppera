package org.khw.shoppera.category.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import org.khw.shoppera.category.domain.entity.QCategoryEntity.categoryEntity
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
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