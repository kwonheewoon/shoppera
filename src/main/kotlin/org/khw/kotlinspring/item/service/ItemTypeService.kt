package org.khw.kotlinspring.item.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.CategoryException
import org.khw.kotlinspring.common.exception.ItemException
import org.khw.kotlinspring.common.exception.ItemTypeException
import org.khw.kotlinspring.item.domain.dto.*
import org.khw.kotlinspring.item.domain.entity.ItemEntityFactory
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntityFactory
import org.khw.kotlinspring.item.domain.mapper.ItemMapper
import org.khw.kotlinspring.item.domain.mapper.ItemTypeMapper
import org.khw.kotlinspring.item.repository.ItemRepository
import org.khw.kotlinspring.item.repository.ItemTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@RequiredArgsConstructor
class ItemTypeService(val itemTypeRepository: ItemTypeRepository,
                      val itemTypeMapper: ItemTypeMapper) {


    @Transactional
    fun findItemTypeByTypeCode(typeCode: String): ItemTypeViewApiDto{
        return itemTypeMapper.entityToViewApiDto(
            itemTypeRepository.findByTypeCodeAndDeleteFlag(typeCode, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }
        )
    }

    @Transactional
    fun saveItemType(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeViewApiDto{
        return itemTypeMapper.entityToViewApiDto(
            itemTypeRepository.save(ItemTypeEntityFactory.createItemTypeEntity(itemTypeSaveDto)
            )
        )
    }

}