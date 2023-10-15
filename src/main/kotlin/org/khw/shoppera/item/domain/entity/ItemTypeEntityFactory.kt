package org.khw.shoppera.item.domain.entity

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemTypeSaveDto

class ItemTypeEntityFactory {

    companion object {
        fun createItemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeEntity{
            return ItemTypeEntity(null, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)
        }
    }
}