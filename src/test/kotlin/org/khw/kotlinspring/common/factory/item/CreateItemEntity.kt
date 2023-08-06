package org.khw.kotlinspring.common.factory.item

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity

class CreateItemEntity {

    companion object{
        fun itemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(null, itemSaveDto.itemName, categoryEntity, itemSaveDto.displayFlag)
        }

        fun savedItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(1, itemSaveDto.itemName, categoryEntity, itemSaveDto.displayFlag)
        }
    }
}