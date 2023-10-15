package org.khw.shoppera.category.factory

import org.khw.shoppera.category.domain.dto.CategoryListApiDto
import org.khw.shoppera.category.domain.dto.CategorySaveDto
import org.khw.shoppera.category.domain.dto.CategoryViewApiDto
import org.khw.shoppera.common.enums.CommonEnum.FlagYn

class CreateCategoryDto {

    companion object {
        fun categorySaveDtoParentExist(): CategorySaveDto{
            return CategorySaveDto(1L, "자식 카테고리", 1, 1, FlagYn.N)
        }

        fun categorySaveDtoParentNonExist(): CategorySaveDto{
            return CategorySaveDto(null, "부모 카테고리", 1, 1, FlagYn.N)
        }

        fun categoryViewApiDtoParentAndChildNonExist(): CategoryViewApiDto{
            return CategoryViewApiDto(1L, "부모 카테고리", 1, 1, FlagYn.N, null)
        }

        fun categoryViewApiDtoParentAndChildExist(): CategoryViewApiDto{
            return CategoryViewApiDto(1L, "자식 카테고리(부모 포함)", 1, 1, FlagYn.N, categoryViewApiDtoParentAndChildNonExist())
        }

        fun categoryListApiDtoParentAndChildExist(): List<CategoryListApiDto>{
            return listOf(CategoryListApiDto(1L, "부모 카테고리", 1, 1, FlagYn.N,
                listOf(CategoryListApiDto(2L, "자식 카테고리1", 2, 1, FlagYn.N, null),
                    CategoryListApiDto(3L, "자식 카테고리2", 2, 2, FlagYn.N, null),
                    CategoryListApiDto(4L, "부모 카테고리3", 2, 3, FlagYn.N, null))
            )
            )
        }
    }
}