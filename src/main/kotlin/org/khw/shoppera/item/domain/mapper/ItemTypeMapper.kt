package org.khw.shoppera.item.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemTypeViewApiDto
import org.khw.shoppera.item.domain.entity.ItemType
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ItemTypeMapper {

    //@Mapping(target = "categoryId", source = "category.id")
    fun entityToViewApiDto(itemType: ItemType): ItemTypeViewApiDto

}