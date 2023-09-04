package org.khw.kotlinspring.item.factory

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntity

class CreateItemEntity {

    companion object{
        fun itemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(null, itemSaveDto.itemName, categoryEntity, itemTypeEntity, itemSaveDto.displayFlag)
        }

        fun savedItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(1, itemSaveDto.itemName, categoryEntity, itemTypeEntity, itemSaveDto.displayFlag)
        }

        fun findItemEntity(categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(1, "하와이안 셔츠", categoryEntity, itemTypeEntity, FlagYn.N)
        }

        fun findItemEntityList(categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): List<ItemEntity>{
            return listOf(
                ItemEntity(1, "하와이안 셔츠", categoryEntity, itemTypeEntity, FlagYn.N),
                ItemEntity(2, "벚꽃 남방", categoryEntity, itemTypeEntity, FlagYn.N),
                ItemEntity(3, "청 자켓", categoryEntity, itemTypeEntity, FlagYn.N)
            )
        }

        fun updatedItemEntity(itemUpdateDto: ItemUpdateDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(1, itemUpdateDto.itemName, categoryEntity, itemTypeEntity, itemUpdateDto.displayFlag, itemUpdateDto.deleteFlag)
        }
    }
}