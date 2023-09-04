package org.khw.kotlinspring.item.factory

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.*

class CreateItemTypeDto {

    companion object{
        fun itemTypeSaveDto(): ItemTypeSaveDto{
            return ItemTypeSaveDto("ELEC", "전자기기", deleteFlag = FlagYn.N)
        }

        fun itemTypeUpdateDto(): ItemTypeUpdateDto{
            return ItemTypeUpdateDto("CLTH", "의류", deleteFlag = FlagYn.N)
        }

        fun itemTypeViewApiDto(typeName: String, typeCode: String, deleteFlag: FlagYn): ItemTypeViewApiDto{
            return ItemTypeViewApiDto(1, typeName, typeCode, deleteFlag = deleteFlag)
        }

    }
}