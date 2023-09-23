package org.khw.kotlinspring.itemoption.mapper


import org.khw.kotlinspring.itemoption.domain.dto.ItemOptionViewApiDto
import org.khw.kotlinspring.itemoption.domain.entity.ItemOption
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ItemOptionMapper {

    @Mapping(target = "itemId", source = "item.id")
    fun entityToViewApiDto(itemOption: ItemOption): ItemOptionViewApiDto

    fun entityListToViewApiDtoList(itemOptionList: List<ItemOption>): List<ItemOptionViewApiDto>
}