package org.khw.shoppera.category.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import org.khw.shoppera.category.domain.entity.QCategory.category
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
            .update(category)
            .set(category.deleteFlag, FlagYn.Y)
            .where(category.id.eq(categoryId))
            .execute()
    }
}