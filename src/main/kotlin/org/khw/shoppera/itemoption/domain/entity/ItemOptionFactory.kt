package org.khw.shoppera.itemoption.domain.entity

import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.entity.ItemEntity
import org.khw.shoppera.itemoption.domain.dto.ItemOptionSaveDto

class ItemOptionFactory {
    companion object{
        fun createItemOptions(itemEntity: ItemEntity, itemSaveDto: ItemSaveDto): List<ItemOption>{
            return itemSaveDto.itemOptionList
                .map { itemOptionSaveDto -> createItemOption(itemEntity, itemOptionSaveDto) }
        }

        fun createItemOptions(itemEntity: ItemEntity, itemUpdateDto: ItemUpdateDto): List<ItemOption>{
            return itemUpdateDto.itemOptionList
                .map { itemOptionSaveDto -> createItemOption(itemEntity, itemOptionSaveDto) }
        }

        private fun createItemOption(itemEntity: ItemEntity, itemOptionSaveDto: ItemOptionSaveDto): ItemOption{
            return ItemOption(null, itemEntity, itemOptionSaveDto.optionName, itemOptionSaveDto.orderNo, itemOptionSaveDto.displayFlag)
        }
    }
}