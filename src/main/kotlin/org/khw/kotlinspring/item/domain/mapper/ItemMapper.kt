package org.khw.kotlinspring.item.domain.mapper


import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.khw.kotlinspring.itemoption.mapper.ItemOptionMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [ItemOptionMapper::class])
interface ItemMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "typeName", source = "itemType.typeName")
    @Mapping(target = "itemOptionList", source = "itemOptionList")
    fun entityToViewApiDto(itemEntity: ItemEntity): ItemViewApiDto

    fun entityListToViewApiDtoList(itemEntityList: List<ItemEntity>): List<ItemViewApiDto>
}