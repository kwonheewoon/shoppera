package org.khw.shoppera.item.domain.entity

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item
import org.khw.shoppera.category.domain.entity.CategoryEntity
import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.itemoption.domain.entity.ItemOption

class ItemEntityFactory {

    companion object {
        fun createItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(null, itemTypeEntity, itemSaveDto.itemName, itemSaveDto.price, categoryEntity, null, itemSaveDto.displayFlag)
        }
    }
}