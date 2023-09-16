package org.khw.kotlinspring.itemoption.domain.entity

import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.itemoption.domain.dto.ItemOptionSaveDto

class ItemOptionFactory {
    companion object{
        fun createItemOptions(itemSaveDto: ItemSaveDto): List<ItemOption>{
            return itemSaveDto.itemOptionList
                .map { itemOptionSaveDto -> createItemOption(itemOptionSaveDto) }
        }

        private fun createItemOption(itemOptionSaveDto: ItemOptionSaveDto): ItemOption{
            return ItemOption(null, null, itemOptionSaveDto.optionName, itemOptionSaveDto.orderNo, itemOptionSaveDto.displayFlag)
        }
    }
}