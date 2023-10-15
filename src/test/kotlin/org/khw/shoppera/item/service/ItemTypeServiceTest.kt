package org.khw.shoppera.item.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.item.domain.mapper.ItemTypeMapper
import org.khw.shoppera.item.repository.ItemTypeRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.khw.shoppera.common.enums.CommonEnum.*
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.ItemTypeException
import org.khw.shoppera.item.domain.entity.ItemTypeEntity
import org.khw.shoppera.item.factory.CreateItemTypeDto
import org.khw.shoppera.item.factory.CreateItemTypeEntity
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockitoExtension::class)
class ItemTypeServiceTest {

    @Mock
    lateinit var itemTypeRepository: ItemTypeRepository

    @Mock
    lateinit var itemTypeMapper: ItemTypeMapper

    @InjectMocks
    lateinit var itemTypeService: ItemTypeService

    @Test
    fun `아이템 타입 등록 실패(중복된 아이템 타입 코드)`(){
        // Given
        val itemTypeSaveDto = CreateItemTypeDto.itemTypeSaveDto()
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()

        given(itemTypeRepository.findByTypeCodeAndDeleteFlag(itemTypeSaveDto.typeCode, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))

        // When
        val throwable = assertThrows(ItemTypeException::class.java) {
            itemTypeService.saveItemType(itemTypeSaveDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_ITEM_TYPE.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_ITEM_TYPE.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_ITEM_TYPE.httpStatus, HttpStatus.CONFLICT)
        verify(itemTypeRepository).findByTypeCodeAndDeleteFlag(itemTypeSaveDto.typeCode, FlagYn.N)
    }

    @Test
    fun `아이템 타입 등록 성공`(){
        // Given
        val itemTypeSaveDto = CreateItemTypeDto.itemTypeSaveDto()
        val itemTypeSavedEntity = CreateItemTypeEntity.savedItemTypeEntity(itemTypeSaveDto)
        val itemTypeViewApiDto = CreateItemTypeDto.itemTypeViewApiDto(itemTypeSaveDto.typeCode, itemTypeSaveDto.typeName, FlagYn.N)

        given(itemTypeRepository.save(any(ItemTypeEntity::class.java)))
            .willReturn(itemTypeSavedEntity)
        given(itemTypeMapper.entityToViewApiDto(itemTypeSavedEntity))
            .willReturn(itemTypeViewApiDto)

        // When
        val result = itemTypeService.saveItemType(itemTypeSaveDto)

        // Then
        assertEquals(result, itemTypeViewApiDto)
        assertEquals(result.typeCode, itemTypeViewApiDto.typeCode)
        assertEquals(result.typeName, itemTypeViewApiDto.typeName)
        verify(itemTypeRepository).save(any(ItemTypeEntity::class.java))
        verify(itemTypeMapper).entityToViewApiDto(itemTypeSavedEntity)
    }

    @Test
    fun `아이템 타입 조회 성공`(){
        // Given
        val itemTypeId = 1L;
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()
        val itemTypeViewApiDto = CreateItemTypeDto.itemTypeViewApiDto(findItemTypeEntity.typeCode, findItemTypeEntity.typeName, FlagYn.N)

        given(itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))
        given(itemTypeMapper.entityToViewApiDto(findItemTypeEntity))
            .willReturn(itemTypeViewApiDto)

        // When
        val result = itemTypeService.findItemTypeByitemTypeId(itemTypeId)

        // Then
        assertEquals(result, itemTypeViewApiDto)
        assertEquals(result.typeCode, itemTypeViewApiDto.typeCode)
        assertEquals(result.typeName, itemTypeViewApiDto.typeName)
        verify(itemTypeRepository).findByIdAndDeleteFlag(itemTypeId, FlagYn.N)
        verify(itemTypeMapper).entityToViewApiDto(findItemTypeEntity)
    }

    @Test
    fun `아이템 타입 조회 실패(존재하지 않는 아이템 타입)`(){
        // Given
        val itemTypeId = 1L;
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()
        val itemTypeViewApiDto = CreateItemTypeDto.itemTypeViewApiDto(findItemTypeEntity.typeCode, findItemTypeEntity.typeName, FlagYn.N)

        given(itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(ItemTypeException::class.java) {
            itemTypeService.findItemTypeByitemTypeId(itemTypeId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ITEM_TYPE.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ITEM_TYPE.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ITEM_TYPE.httpStatus, HttpStatus.NOT_FOUND)
        verify(itemTypeRepository).findByIdAndDeleteFlag(itemTypeId, FlagYn.N)
    }

    @Test
    fun `아이템 타입 수정 성공`(){
        // Given
        val itemTypeId = 1L
        val itemTypeUpdateDto = CreateItemTypeDto.itemTypeUpdateDto()
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()
        val updatedItemTypeEntity = CreateItemTypeEntity.updatedItemEntity(itemTypeUpdateDto)
        val itemTypeViewApiDto = CreateItemTypeDto.itemTypeViewApiDto(updatedItemTypeEntity.typeCode, updatedItemTypeEntity.typeName, FlagYn.N)

        given(itemTypeRepository.findByIdNotAndTypeCodeAndDeleteFlag(itemTypeId, itemTypeUpdateDto.typeCode, FlagYn.N))
            .willReturn(Optional.empty())
        given(itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))
        given(itemTypeMapper.entityToViewApiDto(findItemTypeEntity))
            .willReturn(itemTypeViewApiDto)

        // When
        val result = itemTypeService.updateItemType(itemTypeId, itemTypeUpdateDto)

        // Then
        assertEquals(result, itemTypeViewApiDto)
        assertEquals(updatedItemTypeEntity.typeCode, itemTypeViewApiDto.typeCode)
        assertEquals(updatedItemTypeEntity.typeName, itemTypeViewApiDto.typeName)
        assertEquals(result.typeCode, itemTypeViewApiDto.typeCode)
        assertEquals(result.typeName, itemTypeViewApiDto.typeName)
        verify(itemTypeRepository).findByIdNotAndTypeCodeAndDeleteFlag(itemTypeId, itemTypeUpdateDto.typeCode, FlagYn.N)
        verify(itemTypeRepository).findByIdAndDeleteFlag(itemTypeId, FlagYn.N)
    }

    @Test
    fun `아이템 타입 수정 실패(중복된 아이템 타입 코드)`(){
        // Given
        val itemTypeId = 1L
        val itemTypeUpdateDto = CreateItemTypeDto.itemTypeUpdateDto()
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()

        given(itemTypeRepository.findByIdNotAndTypeCodeAndDeleteFlag(itemTypeId, itemTypeUpdateDto.typeCode, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))

        // When
        val throwable = assertThrows(ItemTypeException::class.java) {
            itemTypeService.updateItemType(itemTypeId, itemTypeUpdateDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_ITEM_TYPE.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_ITEM_TYPE.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_ITEM_TYPE.httpStatus, HttpStatus.CONFLICT)
        verify(itemTypeRepository).findByIdNotAndTypeCodeAndDeleteFlag(itemTypeId, itemTypeUpdateDto.typeCode, FlagYn.N)
    }

    @Test
    fun `아이템 타입 삭제 실패(존재 하지 않는 아이템 타입)`(){
        // Given
        val itemTypeId = 1L
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()

        given(itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(ItemTypeException::class.java) {
            itemTypeService.deleteItemType(itemTypeId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ITEM_TYPE.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ITEM_TYPE.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ITEM_TYPE.httpStatus, HttpStatus.NOT_FOUND)
        verify(itemTypeRepository).findByIdAndDeleteFlag(itemTypeId, FlagYn.N)
    }

    @Test
    fun `아이템 타입 삭제 성공`(){
        // Given
        val itemTypeId = 1L
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()

        given(itemTypeRepository.findByIdAndDeleteFlag(itemTypeId, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))

        // When
        itemTypeService.deleteItemType(itemTypeId)

        // Then
        verify(itemTypeRepository).findByIdAndDeleteFlag(itemTypeId, FlagYn.N)
    }
}