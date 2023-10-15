package org.khw.shoppera.category.domain.mapper

import org.khw.shoppera.category.domain.dto.CategoryApiDto
import org.khw.shoppera.category.domain.dto.CategoryListApiDto
import org.khw.shoppera.category.domain.dto.CategoryViewApiDto
import org.khw.shoppera.category.domain.entity.CategoryEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CategoryMapper {

    fun entityToApiDto(categoryEntity: CategoryEntity) : CategoryApiDto

    //CategoryEntity -> CategoryViewApiDto

    fun entityToViewApiDto(categoryEntity: CategoryEntity) : CategoryViewApiDto

    @Mapping(target = "childCategoryList", ignore = true)
    fun entityListToViewApiDtoList(categoryEntityList : List<CategoryEntity>) : List<CategoryViewApiDto>

    //CategoryEntity -> CategoryListApiDto

    @Mapping(target = "childCategorys", source = "childCategoryList")
    fun entityToListApiDto(categoryEntity: CategoryEntity) : CategoryListApiDto

    fun entityListToListApiDtoList(categoryEntityList : List<CategoryEntity>) : List<CategoryListApiDto>
}