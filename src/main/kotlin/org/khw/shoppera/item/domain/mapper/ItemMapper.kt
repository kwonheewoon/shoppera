package org.khw.shoppera.item.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.ItemEntity
import org.khw.shoppera.itemoption.mapper.ItemOptionMapper
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