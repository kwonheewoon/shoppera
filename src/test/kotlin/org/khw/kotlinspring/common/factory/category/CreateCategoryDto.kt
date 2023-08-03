package org.khw.kotlinspring.common.factory.category

import org.khw.kotlinspring.category.domain.dto.CategorySaveDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn

class CreateCategoryDto {

    companion object {
        fun categorySaveDtoParentExist() : CategorySaveDto{
            return CategorySaveDto(1L, "자식 카테고리", 1, 1, FlagYn.N)
        }

        fun categorySaveDtoParentNonExist() : CategorySaveDto{
            return CategorySaveDto(null, "부모 카테고리", 1, 1, FlagYn.N)
        }

        fun categoryViewApiDtoParentAndChildNonExist() : CategoryViewApiDto{
            return CategoryViewApiDto(1L, "부모 카테고리", 1, 1, FlagYn.N, null)
        }
    }
}