package org.khw.shoppera.item.domain.entity

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemTypeSaveDto

class ItemTypeFactory {

    companion object {
        fun createItemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemType{
            return ItemType(null, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)
        }
    }
}