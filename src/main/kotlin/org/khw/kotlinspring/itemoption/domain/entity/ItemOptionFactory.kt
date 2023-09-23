package org.khw.kotlinspring.itemoption.domain.entity

import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.khw.kotlinspring.itemoption.domain.dto.ItemOptionSaveDto

class ItemOptionFactory {
    companion object{
        fun createItemOptions(itemEntity: ItemEntity, itemSaveDto: ItemSaveDto): List<ItemOption>{
            return itemSaveDto.itemOptionList
                .map { itemOptionSaveDto -> createItemOption(itemEntity, itemOptionSaveDto) }
        }

        private fun createItemOption(itemEntity: ItemEntity, itemOptionSaveDto: ItemOptionSaveDto): ItemOption{
            return ItemOption(null, itemEntity, itemOptionSaveDto.optionName, itemOptionSaveDto.orderNo, itemOptionSaveDto.displayFlag)
        }
    }
}