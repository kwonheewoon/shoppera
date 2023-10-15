package org.khw.shoppera.common.factory.category

import org.khw.shoppera.category.domain.entity.CategoryEntity

class CreateCategoryEntity {

    companion object {
        fun categoryEntityParentNonExist(): CategoryEntity{
            return CategoryEntity(null, "부모 카테고리", 1)
        }

        fun categoryEntityParentExist(): CategoryEntity{
            return CategoryEntity(null, "자식 카테고리", 1, parentCategory = categoryEntityParentNonExist())
        }

        fun categoryEntityParent(): CategoryEntity{
            return CategoryEntity(1L, "부모 카테고리", 1)
        }

        fun categoryEntityChildExistList(): List<CategoryEntity>{
            return listOf(
                CategoryEntity(1L, "부모 카테고리(자식 카테고리 포함)", 1, childCategoryList =
                listOf(
                    CategoryEntity(2L, "자식 카테고리1", 1, depth = 2),
                    CategoryEntity(3L, "자식 카테고리2", 2, depth = 2),
                    CategoryEntity(4L, "자식 카테고리1", 3, depth = 2)
                )
                )
            )
        }
    }
}