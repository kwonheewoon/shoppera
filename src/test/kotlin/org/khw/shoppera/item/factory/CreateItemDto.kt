package org.khw.shoppera.item.factory

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.dto.ItemViewApiDto

class CreateItemDto {

    companion object{
        fun itemSaveDto(): ItemSaveDto{
            return ItemSaveDto("CLTH", "하와이안 셔츠", 65000, 1, 1, displayFlag = FlagYn.N,
                itemOptionList = CreateItemOptionDto.itemOptionSaveDtoList(), deleteFlag = FlagYn.N)
        }

        fun itemUpdateDto(): ItemUpdateDto{
            return ItemUpdateDto("CLTH", "스카쟌 점퍼", 65000, 2, 2, displayFlag = FlagYn.N,
                itemOptionList = CreateItemOptionDto.itemOptionSaveDtoList(), deleteFlag = FlagYn.N)
        }

        fun itemViewApiDto(typeName: String, price: Int, itemName: String, brandId: Long, categoryId: Long, displayFlag: FlagYn, deleteFlag: FlagYn): ItemViewApiDto{
            return ItemViewApiDto(1, typeName, itemName, price, brandId, categoryId, displayFlag = displayFlag,
                itemOptionList = CreateItemOptionDto.itemOptionViewApiDtoList(1), deleteFlag = deleteFlag)
        }

        fun itemViewApiDtoList(): List<ItemViewApiDto>{
            return listOf(
                ItemViewApiDto(1, "의류", "하와이안 셔츠", 65000, 1, 1, displayFlag = FlagYn.N,
                    itemOptionList = CreateItemOptionDto.itemOptionViewApiDtoList(1), deleteFlag = FlagYn.N),
                ItemViewApiDto(2, "의류", "벚꽃 남방", 49650, 1, 1, displayFlag = FlagYn.N,
                    itemOptionList = CreateItemOptionDto.itemOptionViewApiDtoList(2), deleteFlag = FlagYn.N),
                ItemViewApiDto(3, "의류", "청자켓", 123800, 1, 1, displayFlag = FlagYn.N,
                    itemOptionList = CreateItemOptionDto.itemOptionViewApiDtoList(3), deleteFlag = FlagYn.N)
            )
        }
    }
}