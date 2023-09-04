package org.khw.kotlinspring.item.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.item.domain.mapper.ItemTypeMapper
import org.khw.kotlinspring.item.repository.ItemTypeRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.enums.CommonEnum.*
import org.khw.kotlinspring.item.domain.entity.ItemTypeEntity
import org.khw.kotlinspring.item.factory.CreateItemDto
import org.khw.kotlinspring.item.factory.CreateItemTypeDto
import org.khw.kotlinspring.item.factory.CreateItemTypeEntity

@ExtendWith(MockitoExtension::class)
class ItemTypeServiceTest {

    @Mock
    lateinit var itemTypeRepository: ItemTypeRepository

    @Mock
    lateinit var itemTypeMapper: ItemTypeMapper

    @InjectMocks
    lateinit var itemTypeService: ItemTypeService

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
}