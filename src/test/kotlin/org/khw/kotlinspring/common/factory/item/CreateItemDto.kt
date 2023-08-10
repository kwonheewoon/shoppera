package org.khw.kotlinspring.common.factory.item

import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto

class CreateItemDto {

    companion object{
        fun itemSaveDto(): ItemSaveDto{
            return ItemSaveDto("하와이안 셔츠", 1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        }

        fun itemUpdateDto(): ItemUpdateDto{
            return ItemUpdateDto("스카쟌 점퍼", 1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        }

        fun itemViewApiDto(itemName: String, categoryId: Long, displayFlag: FlagYn, deleteFlag: FlagYn): ItemViewApiDto{
            return ItemViewApiDto(1, itemName, categoryId, displayFlag = displayFlag, deleteFlag = deleteFlag)
        }
    }
}