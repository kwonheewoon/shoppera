package org.khw.shoppera.itemoption.domain.entity

import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.domain.dto.ItemOptionSaveDto

class ItemOptionFactory {
    companion object{
        fun createItemOptions(item: Item, itemSaveDto: ItemSaveDto): List<ItemOption>{
            return itemSaveDto.itemOptionList
                .map { itemOptionSaveDto -> createItemOption(item, itemOptionSaveDto) }
        }

        fun createItemOptions(item: Item, itemUpdateDto: ItemUpdateDto): List<ItemOption>{
            return itemUpdateDto.itemOptionList
                .map { itemOptionSaveDto -> createItemOption(item, itemOptionSaveDto) }
        }

        private fun createItemOption(item: Item, itemOptionSaveDto: ItemOptionSaveDto): ItemOption{
            return ItemOption(null, item, itemOptionSaveDto.optionName, itemOptionSaveDto.orderNo, itemOptionSaveDto.displayFlag)
        }
    }
}