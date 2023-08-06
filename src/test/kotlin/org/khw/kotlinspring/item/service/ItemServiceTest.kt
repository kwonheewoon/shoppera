package org.khw.kotlinspring.item.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.category.repository.CategoryRepository

import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.common.factory.category.CreateCategoryEntity
import org.khw.kotlinspring.common.factory.item.CreateItemDto
import org.khw.kotlinspring.common.factory.item.CreateItemEntity
import org.khw.kotlinspring.item.domain.mapper.ItemMapper
import org.khw.kotlinspring.item.repository.ItemRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.util.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.khw.kotlinspring.item.domain.entity.ItemEntity

@ExtendWith(MockitoExtension::class)
class ItemServiceTest(

)
{
@Mock
lateinit var itemRepository: ItemRepository

@Mock
lateinit var categoryRepository: CategoryRepository

@Mock
lateinit var itemMapper: ItemMapper

@InjectMocks
lateinit var itemService: ItemService


    @Test
    fun `아이템등록 성공`(){
        // Given
        val itemSaveDto = CreateItemDto.itemSaveDto()
        val itemViewApiDto = CreateItemDto.itemViewApiDto()
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val createdItemEntity = CreateItemEntity.itemEntity(itemSaveDto, findCategoryEntity)
        val savedItemEntity = CreateItemEntity.savedItemEntity(itemSaveDto, findCategoryEntity)

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategoryEntity))
        given(itemRepository.save(any(ItemEntity::class.java)))
            .willReturn(savedItemEntity)
        given(itemMapper.entityToViewApiDto(savedItemEntity))
            .willReturn(itemViewApiDto)

        // When
        val result = itemService.saveItem(itemSaveDto)

        // Then
        assertEquals(result, itemViewApiDto)
        assertEquals(itemViewApiDto.itemName, "하와이안 셔츠")
        verify(categoryRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemRepository).save(any(ItemEntity::class.java))
    }
}