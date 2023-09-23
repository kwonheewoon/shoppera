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
            return ItemEntity(null, itemTypeEntity, itemSaveDto.itemName, 65000, categoryEntity, CreateItemOptionEntity.savedItemOptions(itemSaveDto.itemOptionList) , itemSaveDto.displayFlag)
        }

        fun savedItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(1, itemTypeEntity, itemSaveDto.itemName, 65000, categoryEntity, CreateItemOptionEntity.savedItemOptions(itemSaveDto.itemOptionList), itemSaveDto.displayFlag)
        }

        fun findItemEntity(categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{

            val findItem = ItemEntity(1, itemTypeEntity, "하와이안 셔츠", 65000, categoryEntity, null, FlagYn.N)

            findItem.optionAdd(CreateItemOptionEntity.findItemOptions(findItem))
            return findItem

        }

        fun findItemEntityList(categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): List<ItemEntity>{

            return listOf(
                ItemEntity(1, itemTypeEntity, "하와이안 셔츠", 65000, categoryEntity, null, FlagYn.N),
                ItemEntity(2, itemTypeEntity, "벚꽃 남방", 65000, categoryEntity, null, FlagYn.N),
                ItemEntity(3, itemTypeEntity, "청 자켓", 65000, categoryEntity, null, FlagYn.N)
            ).onEach { itemEntity ->  itemEntity.optionAdd(CreateItemOptionEntity.findItemOptions(itemEntity)) }
        }

        fun updatedItemEntity(itemUpdateDto: ItemUpdateDto, categoryEntity: CategoryEntity, itemTypeEntity: ItemTypeEntity): ItemEntity{
            return ItemEntity(1, itemTypeEntity, itemUpdateDto.itemName, 65000, categoryEntity, CreateItemOptionEntity.savedItemOptions(itemUpdateDto.itemOptionList), itemUpdateDto.displayFlag, itemUpdateDto.deleteFlag)
        }
    }
}