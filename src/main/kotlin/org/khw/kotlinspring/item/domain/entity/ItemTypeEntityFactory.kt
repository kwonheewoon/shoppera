package org.khw.kotlinspring.item.domain.entity

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemTypeSaveDto

class ItemTypeEntityFactory {

    companion object {
        fun createItemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeEntity{
            return ItemTypeEntity(null, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)
        }
    }
}