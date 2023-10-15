package org.khw.shoppera.item.factory

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.*

class CreateItemTypeDto {

    companion object{
        fun itemTypeSaveDto(): ItemTypeSaveDto{
            return ItemTypeSaveDto("ELEC", "전자기기")
        }

        fun itemTypeUpdateDto(): ItemTypeUpdateDto{
            return ItemTypeUpdateDto("CLTH", "의류")
        }

        fun itemTypeViewApiDto(typeName: String, typeCode: String, deleteFlag: FlagYn): ItemTypeViewApiDto{
            return ItemTypeViewApiDto(1, typeName, typeCode, deleteFlag = deleteFlag)
        }

    }
}