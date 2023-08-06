package org.khw.kotlinspring.item.service

import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemSaveDto
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

    @Transactional
    fun saveItem(itemSaveDto: ItemSaveDto): ItemViewApiDto{
        val findCategoryEntity = categoryRepository.findByIdAndDeleteFlag(itemSaveDto.cateogryId, FlagYn.N).orElseThrow { IllegalStateException("존재하지 않는 카테고리 정보입니다.") }

        return itemMapper.entityToViewApiDto(itemRepository.save(ItemEntityFactory.createItenEntity(itemSaveDto, findCategoryEntity)))
    }

}