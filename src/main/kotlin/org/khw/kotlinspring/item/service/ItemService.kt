package org.khw.kotlinspring.item.service

import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.CategoryException
import org.khw.kotlinspring.common.exception.ItemException
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto
import org.khw.kotlinspring.item.domain.dto.ItemViewApiDto
import org.khw.kotlinspring.item.domain.entity.ItemEntityFactory
import org.khw.kotlinspring.item.domain.mapper.ItemMapper
import org.khw.kotlinspring.item.repository.ItemRepository
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class ItemService(val itemRepository: ItemRepository,
                  val categoryRepository: CategoryRepository,
    val itemMapper: ItemMapper) {

    @Transactional()
    fun findItem(itemId: Long): ItemViewApiDto{
        val findItemEntity = itemRepository.findByIdAndDeleteFlag(itemId, FlagYn.N).orElseThrow{ ItemException(
            ResCode.NOT_FOUND_ITEM) }
        return itemMapper.entityToViewApiDto(findItemEntity)
    }

    @Transactional()
    fun findAllItem(categoryId: Long): List<ItemViewApiDto>{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(categoryId, FlagYn.N).orElseThrow { CategoryException(ResCode.NOT_FOUND_CATEGORY) }

        val findItemEntityList = itemRepository.findByCategoryAndDeleteFlag(findCategoryEntity, FlagYn.N)
        return itemMapper.entityListToViewApiDtoList(findItemEntityList)
    }

    @Transactional
    fun saveItem(itemSaveDto: ItemSaveDto): ItemViewApiDto{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(itemSaveDto.categoryId, FlagYn.N).orElseThrow { CategoryException(ResCode.NOT_FOUND_CATEGORY) }

        return itemMapper.entityToViewApiDto(itemRepository.save(ItemEntityFactory.createItenEntity(itemSaveDto, findCategoryEntity)))
    }

    @Transactional
    fun updateItem(itemId: Long, itemUpdateDto: ItemUpdateDto): ItemViewApiDto{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(itemUpdateDto.categoryId, FlagYn.N).orElseThrow { CategoryException(ResCode.NOT_FOUND_CATEGORY) }
        val findItemEntity = itemRepository.findByIdAndDeleteFlag(itemId, FlagYn.N).orElseThrow{ ItemException(
            ResCode.NOT_FOUND_ITEM) }

        findItemEntity.updateItem(itemUpdateDto, findCategoryEntity)

        return itemMapper.entityToViewApiDto(findItemEntity)
    }

}