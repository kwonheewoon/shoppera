package org.khw.shoppera.category.factory

import org.khw.shoppera.category.domain.entity.Category

class CreateCategoryEntity {

    companion object {
        fun categoryEntityParentNonExist(): Category{
            return Category(null, "부모 카테고리", 1)
        }

        fun categoryEntityParentExist(): Category{
            return Category(null, "자식 카테고리", 1, parentCategory = categoryEntityParentNonExist())
        }

        fun categoryEntityParent(): Category{
            return Category(1L, "부모 카테고리", 1)
        }

        fun categoryEntityChildExistList(): List<Category>{
            return listOf(
                Category(1L, "부모 카테고리(자식 카테고리 포함)", 1, childCategoryList =
                listOf(
                    Category(2L, "자식 카테고리1", 1, depth = 2),
                    Category(3L, "자식 카테고리2", 2, depth = 2),
                    Category(4L, "자식 카테고리1", 3, depth = 2)
                )
                )
            )
        }
    }
}