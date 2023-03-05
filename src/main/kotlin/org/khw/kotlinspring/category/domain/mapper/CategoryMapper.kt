package org.khw.kotlinspring.category.domain.mapper

import org.khw.kotlinspring.category.domain.dto.CategoryApiDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CategoryMapper {
    /*
    *   @Mappings(
        Mapping(source = "majorVersion", target = "major"),
        Mapping(source = "minorVersion", target = "minor"),
        Mapping(source = "patchVersion", target = "patch"),
        Mapping(source = "normalVersion", target = "normal"),
        Mapping(source = "preReleaseVersion", target = "preRelease")
)
    * */
    fun entityToApiDto(categoryEntity: CategoryEntity) : CategoryApiDto

    fun entityToViewApiDto(categoryEntity: CategoryEntity) : CategoryViewApiDto

    @Mapping(target = "childCategoryList", ignore = true)
    fun entityListToViewApiDtoList(categoryEntityList : List<CategoryEntity>) : List<CategoryViewApiDto>
}