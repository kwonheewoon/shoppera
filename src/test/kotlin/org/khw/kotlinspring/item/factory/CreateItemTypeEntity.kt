package org.khw.kotlinspring.item.factory

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemTypeSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemTypeUpdateDto
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntity

class CreateItemTypeEntity {

    companion object{
        fun itemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeEntity{
            return ItemTypeEntity(null, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)
        }

        fun savedItemTypeEntity(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeEntity{
            return ItemTypeEntity(1, itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, itemTypeSaveDto.deleteFlag)
        }

        fun findItemEntity(): ItemTypeEntity{
            return ItemTypeEntity(1, "ELEC", "전자기기", FlagYn.N)
        }

        fun updatedItemEntity(itemTypeUpdateDto: ItemTypeUpdateDto): ItemTypeEntity{
            return ItemTypeEntity(1, itemTypeUpdateDto.typeCode, itemTypeUpdateDto.typeName, itemTypeUpdateDto.deleteFlag)
        }
    }
}