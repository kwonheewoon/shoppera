package org.khw.kotlinspring.item.domain.mapper


import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ItemMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "typeName", source = "itemType.typeName")
    fun entityToViewApiDto(itemEntity: ItemEntity): ItemViewApiDto

    fun entityListToViewApiDtoList(itemEntityList: List<ItemEntity>): List<ItemViewApiDto>
}