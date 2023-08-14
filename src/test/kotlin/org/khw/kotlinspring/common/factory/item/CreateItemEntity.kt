package org.khw.kotlinspring.common.factory.item

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto
import org.khw.kotlinspring.item.domain.entity.ItemEntity

class CreateItemEntity {

    companion object{
        fun itemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(null, itemSaveDto.itemName, categoryEntity, itemSaveDto.displayFlag)
        }

        fun savedItemEntity(itemSaveDto: ItemSaveDto, categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(1, itemSaveDto.itemName, categoryEntity, itemSaveDto.displayFlag)
        }

        fun findItemEntity(categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(1, "하와이안 셔츠", categoryEntity, FlagYn.N)
        }

        fun findItemEntityList(categoryEntity: CategoryEntity): List<ItemEntity>{
            return listOf(
                ItemEntity(1, "하와이안 셔츠", categoryEntity, FlagYn.N),
                ItemEntity(2, "벚꽃 남방", categoryEntity, FlagYn.N),
                ItemEntity(3, "청 자켓", categoryEntity, FlagYn.N)
            )
        }

        fun updatedItemEntity(itemUpdateDto: ItemUpdateDto, categoryEntity: CategoryEntity): ItemEntity{
            return ItemEntity(1, itemUpdateDto.itemName, categoryEntity, itemUpdateDto.displayFlag, itemUpdateDto.deleteFlag)
        }
    }
}