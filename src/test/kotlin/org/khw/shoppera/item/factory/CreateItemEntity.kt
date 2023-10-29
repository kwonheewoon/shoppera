package org.khw.shoppera.item.factory

import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.item.domain.entity.ItemType

class CreateItemEntity {

    companion object{
        fun itemEntity(itemSaveDto: ItemSaveDto, brand: Brand, category: Category, itemType: ItemType): Item{
            return Item(null, itemType, itemSaveDto.itemName, 65000, brand, category, CreateItemOptionEntity.savedItemOptions(itemSaveDto.itemOptionList) , itemSaveDto.displayFlag)
        }

        fun savedItemEntity(itemSaveDto: ItemSaveDto, brand: Brand, category: Category, itemType: ItemType): Item{
            return Item(1, itemType, itemSaveDto.itemName, 65000, brand, category, CreateItemOptionEntity.savedItemOptions(itemSaveDto.itemOptionList), itemSaveDto.displayFlag)
        }

        fun findItemEntity(brand: Brand, category: Category, itemType: ItemType): Item{

            val findItem = Item(1, itemType, "하와이안 셔츠", 65000, brand, category, null, FlagYn.N)

            findItem.optionAdd(CreateItemOptionEntity.findItemOptions(findItem))
            return findItem

        }

        fun findItemEntityList(brand: Brand, category: Category, itemType: ItemType): List<Item>{

            return listOf(
                Item(1, itemType, "하와이안 셔츠", 65000, brand, category, null, FlagYn.N),
                Item(2, itemType, "벚꽃 남방", 65000, brand, category, null, FlagYn.N),
                Item(3, itemType, "청자켓", 65000, brand, category, null, FlagYn.N)
            ).onEach { itemEntity ->  itemEntity.optionAdd(CreateItemOptionEntity.findItemOptions(itemEntity)) }
        }

        fun updatedItemEntity(itemUpdateDto: ItemUpdateDto, brand: Brand, category: Category, itemType: ItemType): Item{
            return Item(1, itemType, itemUpdateDto.itemName, 65000, brand, category, CreateItemOptionEntity.savedItemOptions(itemUpdateDto.itemOptionList), itemUpdateDto.displayFlag, itemUpdateDto.deleteFlag)
        }
    }
}