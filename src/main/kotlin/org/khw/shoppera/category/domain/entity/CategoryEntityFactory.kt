package org.khw.shoppera.category.domain.entity

import org.khw.shoppera.category.domain.dto.CategorySaveDto

class CategoryEntityFactory {

    companion object {
        fun createCategoryEntityParentExist(categorySaveDto: CategorySaveDto, parentCategory: Category) : Category{
            return Category(null, categorySaveDto.categoryNm, categorySaveDto.orderNo, parentCategory = parentCategory, depth = parentCategory.depth + 1)
        }

        fun createCategoryEntityParentNonExist(categorySaveDto: CategorySaveDto) : Category{
            return Category(null, categorySaveDto.categoryNm, categorySaveDto.orderNo)
        }
    }
}