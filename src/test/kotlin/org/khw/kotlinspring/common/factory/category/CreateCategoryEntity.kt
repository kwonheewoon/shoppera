package org.khw.kotlinspring.common.factory.category

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.CommonEnum.FlagYn

class CreateCategoryEntity {

    companion object {
        fun categoryEntityParentNonExist() : CategoryEntity{
            return CategoryEntity(null, "부모 카테고리", 1, null, 1, FlagYn.N)
        }

        fun categoryEntityParentExist() : CategoryEntity{
            return CategoryEntity(null, "자식 카테고리", 1, categoryEntityParentNonExist(), 2, FlagYn.N)
        }
    }
}