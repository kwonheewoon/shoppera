package org.khw.shoppera.item.factory

import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.domain.dto.ItemOptionSaveDto
import org.khw.shoppera.itemoption.domain.entity.ItemOption

class CreateItemOptionEntity {

    companion object{
        fun savedItemOptions(itemOptions: List<ItemOptionSaveDto>): List<ItemOption>{
            return itemOptions.map { itemOptionSaveDto -> ItemOption(null, null, itemOptionSaveDto.optionName, itemOptionSaveDto.orderNo) }
        }

        fun findItemOptions(item: Item): List<ItemOption>{
            return listOf(ItemOption(1, item, "S", 1),
                ItemOption(2, item, "M", 1),
                ItemOption(3, item, "L", 1),
                ItemOption(4, item, "XL", 1))
        }
    }
}