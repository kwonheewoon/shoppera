package org.khw.kotlinspring.item.factory

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.itemoption.domain.dto.ItemOptionSaveDto
import org.khw.kotlinspring.itemoption.domain.dto.ItemOptionViewApiDto

class CreateItemOptionDto {

    companion object{
        fun itemOptionSaveDto(): ItemOptionSaveDto{
            return ItemOptionSaveDto("XL", 1, FlagYn.N)
        }

        fun itemOptionViewApiDto(): ItemOptionViewApiDto {
            return ItemOptionViewApiDto(1, 1, "XL", 1, FlagYn.Y, FlagYn.N)
        }

        fun itemOptionSaveDtoList(): List<ItemOptionSaveDto>{
            return listOf(ItemOptionSaveDto("S", 1, FlagYn.N),
                ItemOptionSaveDto("M", 2, FlagYn.N),
                ItemOptionSaveDto("L", 3, FlagYn.N),
                ItemOptionSaveDto("XL", 4, FlagYn.N))
        }

        fun itemOptionViewApiDtoList(itemid: Long): List<ItemOptionViewApiDto>{
            return listOf(ItemOptionViewApiDto(1, itemid, "S", 1, FlagYn.Y, FlagYn.N),
                ItemOptionViewApiDto(2, itemid, "M", 2, FlagYn.Y, FlagYn.N),
                ItemOptionViewApiDto(3, itemid, "L", 3, FlagYn.Y, FlagYn.N),
                ItemOptionViewApiDto(4, itemid, "XL", 4, FlagYn.Y, FlagYn.N))
        }
    }
}