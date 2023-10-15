package org.khw.shoppera.item.factory

import org.khw.shoppera.item.domain.entity.ItemEntity
import org.khw.shoppera.itemoption.domain.dto.ItemOptionSaveDto
import org.khw.shoppera.itemoption.domain.entity.ItemOption

class CreateItemOptionEntity {

    companion object{
        fun savedItemOptions(itemOptions: List<ItemOptionSaveDto>): List<ItemOption>{
            return itemOptions.map { itemOptionSaveDto -> ItemOption(null, null, itemOptionSaveDto.optionName, itemOptionSaveDto.orderNo) }
        }

        fun findItemOptions(itemEntity: ItemEntity): List<ItemOption>{
            return listOf(ItemOption(1, itemEntity, "S", 1),
                ItemOption(2, itemEntity, "M", 1),
                ItemOption(3, itemEntity, "L", 1),
                ItemOption(4, itemEntity, "XL", 1))
        }
    }
}