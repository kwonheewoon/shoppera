package org.khw.shoppera.item.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.mapper.ItemOptionMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [ItemOptionMapper::class])
interface ItemMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "typeName", source = "itemType.typeName")
    @Mapping(target = "itemOptionList", source = "itemOptionList")
    fun entityToViewApiDto(item: Item): ItemViewApiDto

    fun entityListToViewApiDtoList(itemList: List<Item>): List<ItemViewApiDto>
}