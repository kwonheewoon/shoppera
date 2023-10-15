package org.khw.shoppera.category.domain.entity

import org.khw.shoppera.category.domain.dto.CategorySaveDto

class CategoryEntityFactory {

    companion object {
        fun createCategoryEntityParentExist(categorySaveDto: CategorySaveDto, parentCategoryEntity: CategoryEntity) : CategoryEntity{
            return CategoryEntity(null, categorySaveDto.categoryNm, categorySaveDto.orderNo, parentCategory = parentCategoryEntity, depth = parentCategoryEntity.depth + 1)
        }

        fun createCategoryEntityParentNonExist(categorySaveDto: CategorySaveDto) : CategoryEntity{
            return CategoryEntity(null, categorySaveDto.categoryNm, categorySaveDto.orderNo)
        }
    }
}