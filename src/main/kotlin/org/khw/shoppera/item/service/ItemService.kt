package org.khw.shoppera.item.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.category.repository.CategoryRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.CategoryException
import org.khw.shoppera.common.exception.ItemException
import org.khw.shoppera.common.exception.ItemTypeException
import org.khw.shoppera.item.domain.dto.ItemSaveDto
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.dto.ItemViewApiDto
import org.khw.shoppera.item.domain.entity.ItemEntityFactory
import org.khw.shoppera.item.domain.mapper.ItemMapper
import org.khw.shoppera.item.repository.ItemRepository
import org.khw.shoppera.item.repository.ItemTypeRepository
import org.khw.shoppera.itemoption.domain.entity.ItemOptionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@RequiredArgsConstructor
class ItemService(val itemRepository: ItemRepository,
                  val itemTypeRepository: ItemTypeRepository,
                  val categoryRepository: CategoryRepository,
    val itemMapper: ItemMapper) {

    @Transactional(readOnly = true)
    fun findItem(itemId: Long): ItemViewApiDto{
        val findItemEntity = itemRepository.findByIdAndDeleteFlag(itemId, FlagYn.N).orElseThrow{ ItemException(
            ResCode.NOT_FOUND_ITEM) }
        return itemMapper.entityToViewApiDto(findItemEntity)
    }

    @Transactional
    fun findAllItem(categoryId: Long): List<ItemViewApiDto>{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(categoryId, FlagYn.N).orElseThrow { CategoryException(ResCode.NOT_FOUND_CATEGORY) }

        val findItemEntityList = itemRepository.findByCategoryAndDeleteFlag(findCategoryEntity, FlagYn.N)
        return itemMapper.entityListToViewApiDtoList(findItemEntityList)
    }

    @Transactional
    fun saveItem(itemSaveDto: ItemSaveDto): ItemViewApiDto{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(itemSaveDto.categoryId, FlagYn.N).orElseThrow { CategoryException(ResCode.NOT_FOUND_CATEGORY) }
        val findItemTypeEntity = itemTypeRepository.findByTypeCodeAndDeleteFlag(itemSaveDto.typeCode, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }

        val item = ItemEntityFactory.createItemEntity(itemSaveDto, findCategoryEntity, findItemTypeEntity)
        item.optionAdd(ItemOptionFactory.createItemOptions(item, itemSaveDto))

        return itemMapper.entityToViewApiDto(
            itemRepository.save(item)
        )
    }

    @Transactional
    fun updateItem(itemId: Long, itemUpdateDto: ItemUpdateDto): ItemViewApiDto{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(itemUpdateDto.categoryId, FlagYn.N).orElseThrow { CategoryException(ResCode.NOT_FOUND_CATEGORY) }
        val findItemTypeEntity = itemTypeRepository.findByTypeCodeAndDeleteFlag(itemUpdateDto.typeCode, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }

        val findItem = itemRepository.findByIdAndDeleteFlag(itemId, FlagYn.N).orElseThrow{ ItemException(
            ResCode.NOT_FOUND_ITEM) }

        findItem.itemOptionList?.forEach { itemOption ->  itemOption.delete()}

        findItem.update(itemUpdateDto, findCategoryEntity, findItemTypeEntity)

        findItem.optionAdd(ItemOptionFactory.createItemOptions(findItem, itemUpdateDto))

        return itemMapper.entityToViewApiDto(findItem)
    }

    @Transactional
    fun deleteItem(itemId: Long){
        val findItem = itemRepository.findByIdAndDeleteFlag(itemId, FlagYn.N).orElseThrow{ ItemException(
            ResCode.NOT_FOUND_ITEM) }

        findItem.itemOptionList?.forEach { itemOption ->  itemOption.delete()}

        findItem.delete()
    }

}