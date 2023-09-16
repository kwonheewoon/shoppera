package org.khw.kotlinspring.item.domain.entity

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.itemoption.domain.entity.ItemOption

class ItemEntityFactory {

    companion object {
        fun createItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity, itemOptionList: List<ItemOption>): ItemEntity{
            return ItemEntity(null, itemTypeEntity, itemSaveDto.itemName, itemSaveDto.price, categoryEntity, itemOptionList, itemSaveDto.displayFlag)
        }
    }
}