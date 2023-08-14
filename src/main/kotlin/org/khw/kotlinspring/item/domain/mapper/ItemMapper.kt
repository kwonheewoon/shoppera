package org.khw.kotlinspring.item.domain.mapper

import org.khw.kotlinspring.category.domain.dto.CategoryApiDto
import org.khw.kotlinspring.category.domain.dto.CategoryListApiDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ItemMapper {

    @Mapping(target = "categoryId", source = "category.id")
    fun entityToViewApiDto(itemEntity: ItemEntity): ItemViewApiDto

    fun entityListToViewApiDtoList(itemEntityList: List<ItemEntity>): List<ItemViewApiDto>
}