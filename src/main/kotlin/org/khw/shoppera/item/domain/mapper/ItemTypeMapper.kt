package org.khw.shoppera.item.domain.mapper


import org.khw.shoppera.item.domain.dto.ItemTypeViewApiDto
import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.ItemEntity
import org.khw.shoppera.item.domain.entity.ItemTypeEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ItemTypeMapper {

    //@Mapping(target = "categoryId", source = "category.id")
    fun entityToViewApiDto(itemTypeEntity: ItemTypeEntity): ItemTypeViewApiDto

}