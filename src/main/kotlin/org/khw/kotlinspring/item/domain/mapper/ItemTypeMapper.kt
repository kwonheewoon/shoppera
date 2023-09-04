package org.khw.kotlinspring.item.domain.mapper


import org.khw.kotlinspring.item.domain.dto.ItemTypeViewApiDto
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ItemTypeMapper {

    //@Mapping(target = "categoryId", source = "category.id")
    fun entityToViewApiDto(itemTypeEntity: ItemTypeEntity): ItemTypeViewApiDto

}