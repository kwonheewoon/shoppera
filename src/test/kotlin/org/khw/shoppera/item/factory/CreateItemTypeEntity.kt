package org.khw.shoppera.item.factory

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemTypeSaveDto
import org.khw.shoppera.item.domain.dto.ItemTypeUpdateDto
import org.khw.shoppera.item.domain.entity.ItemType

class CreateItemTypeEntity {

    companion object{
        fun itemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemType{
            return ItemType(null, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)
        }

        fun savedItemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemType{
            return ItemType(1, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)
        }

        fun findItemTypeEntity(): ItemType{
            return ItemType(1, "ELEC", "전자기기", FlagYn.N)
        }

        fun updatedItemEntity(itemTypeUpdateDto: ItemTypeUpdateDto): ItemType{
            return ItemType(1, itemTypeUpdateDto.typeCode, itemTypeUpdateDto.typeName, FlagYn.N)
        }
    }
}