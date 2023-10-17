package org.khw.shoppera.item.domain.entity

import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.item.domain.dto.ItemSaveDto

class ItemFactory {

    companion object {
        fun createItemEntity(itemSaveDto: ItemSaveDto, brand: Brand, category: Category, itemType: ItemType): org.khw.shoppera.item.domain.entity.Item {
            return Item(null, itemType, itemSaveDto.itemName, itemSaveDto.price, brand, category, null, itemSaveDto.displayFlag)
        }
    }
}