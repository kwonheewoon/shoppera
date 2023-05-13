package org.khw.kotlinspring.category.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.entity.QCategoryEntity
import org.khw.kotlinspring.category.domain.entity.QCategoryEntity.categoryEntity
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn
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
            .set(categoryEntity.deleteFlag, FlagYn.Y)
            .where(categoryEntity.id.eq(categoryId))
            .execute()
    }
}