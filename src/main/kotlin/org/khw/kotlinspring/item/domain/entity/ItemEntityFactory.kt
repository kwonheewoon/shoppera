package org.khw.kotlinspring.item.domain.entity

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto

class ItemEntityFactory {

    companion object {
        fun createItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(null, itemSaveDto.itemName, categoryEntity, itemTypeEntity, itemSaveDto.displayFlag)
        }
    }
}