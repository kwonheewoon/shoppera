package org.khw.shoppera.category.domain.mapper

import org.khw.shoppera.category.domain.dto.CategoryApiDto
import org.khw.shoppera.category.domain.dto.CategoryListApiDto
import org.khw.shoppera.category.domain.dto.CategoryViewApiDto
import org.khw.shoppera.category.domain.entity.Category
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CategoryMapper {

    fun entityToApiDto(category: Category) : CategoryApiDto

    //CategoryEntity -> CategoryViewApiDto

    fun entityToViewApiDto(category: Category) : CategoryViewApiDto

    @Mapping(target = "childCategoryList", ignore = true)
    fun entityListToViewApiDtoList(categoryList : List<Category>) : List<CategoryViewApiDto>

    //CategoryEntity -> CategoryListApiDto

    @Mapping(target = "childCategorys", source = "childCategoryList")
    fun entityToListApiDto(category: Category) : CategoryListApiDto

    fun entityListToListApiDtoList(categoryList : List<Category>) : List<CategoryListApiDto>
}