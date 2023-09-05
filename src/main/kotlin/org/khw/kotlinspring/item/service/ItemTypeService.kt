package org.khw.kotlinspring.item.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.ItemTypeException
import org.khw.kotlinspring.item.domain.dto.*
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntityFactory
import org.khw.kotlinspring.item.domain.mapper.ItemTypeMapper
import org.khw.kotlinspring.item.repository.ItemTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@RequiredArgsConstructor
class ItemTypeService(val itemTypeRepository: ItemTypeRepository,
                      val itemTypeMapper: ItemTypeMapper) {


    @Transactional(readOnly = true)
    fun findItemTypeByTypeCode(typeCode: String): ItemTypeViewApiDto{
        return itemTypeMapper.entityToViewApiDto(
            itemTypeRepository.findByTypeCodeAndDeleteFlag(typeCode, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }
        )
    }

    @Transactional
    fun saveItemType(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeViewApiDto{
        itemTypeRepository.findByTypeCodeAndDeleteFlag(itemTypeSaveDto.typeCode, FlagYn.N).ifPresent {
            throw ItemTypeException(ResCode.DUPLICATE_ITEM_TYPE)
        }

        return itemTypeMapper.entityToViewApiDto(
            itemTypeRepository.save(ItemTypeEntityFactory.createItemTypeEntity(itemTypeSaveDto)
            )
        )
    }

    @Transactional
    fun updateItemType(itemTypeId: Long, itemTypeUpdateDto: ItemTypeUpdateDto): ItemTypeViewApiDto{
        itemTypeRepository.findByIdNotAndTypeCodeAndDeleteFlag(itemTypeId, itemTypeUpdateDto.typeCode, FlagYn.N).ifPresent {
            throw ItemTypeException(ResCode.DUPLICATE_ITEM_TYPE)
        }

        val findItemTypeEntity = itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }
        findItemTypeEntity.update(itemTypeUpdateDto)

        return itemTypeMapper.entityToViewApiDto(findItemTypeEntity)
    }

}