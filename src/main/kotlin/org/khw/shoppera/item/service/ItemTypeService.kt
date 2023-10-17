package org.khw.shoppera.item.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.ItemTypeException
import org.khw.shoppera.item.domain.dto.*
import org.khw.shoppera.item.domain.entity.ItemTypeFactory
import org.khw.shoppera.item.domain.mapper.ItemTypeMapper
import org.khw.shoppera.item.repository.ItemTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@RequiredArgsConstructor
class ItemTypeService(val itemTypeRepository: ItemTypeRepository,
                      val itemTypeMapper: ItemTypeMapper) {


    @Transactional(readOnly = true)
    fun findItemTypeByitemTypeId(itemTypeId: Long): ItemTypeViewApiDto{
        return itemTypeMapper.entityToViewApiDto(
            itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }
        )
    }

    @Transactional
    fun saveItemType(itemTypeSaveDto: ItemTypeSaveDto): ItemTypeViewApiDto{
        itemTypeRepository.findByTypeCodeAndDeleteFlag(itemTypeSaveDto.typeCode, FlagYn.N).ifPresent {
            throw ItemTypeException(ResCode.DUPLICATE_ITEM_TYPE)
        }

        return itemTypeMapper.entityToViewApiDto(
            itemTypeRepository.save(ItemTypeFactory.createItemTypeEntity(itemTypeSaveDto)
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

    @Transactional
    fun deleteItemType(itemTypeId: Long){
        val findItemTypeEntity = itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N).orElseThrow { ItemTypeException(ResCode.NOT_FOUND_ITEM_TYPE) }

        findItemTypeEntity.delete()
    }

}