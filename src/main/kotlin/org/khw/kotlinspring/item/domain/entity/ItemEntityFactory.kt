package org.khw.kotlinspring.item.domain.entity

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto

class ItemEntityFactory {

    companion object {
        fun createItenEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(null, itemSaveDto.itemName, categoryEntity, itemSaveDto.displayFlag)
        }
    }
}