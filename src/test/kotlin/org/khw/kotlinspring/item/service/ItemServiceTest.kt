package org.khw.kotlinspring.item.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.category.repository.CategoryRepository

import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.factory.category.CreateCategoryEntity
import org.khw.kotlinspring.item.factory.CreateItemDto
import org.khw.kotlinspring.item.factory.CreateItemEntity
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
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.CategoryException
import org.khw.kotlinspring.common.exception.ItemException
import org.khw.kotlinspring.item.domain.entity.ItemEntity
import org.khw.kotlinspring.item.factory.CreateItemTypeEntity
import org.khw.kotlinspring.item.repository.ItemTypeRepository
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class ItemServiceTest(

)
{
@Mock
lateinit var itemRepository: ItemRepository

@Mock
lateinit var itemTypeRepository: ItemTypeRepository

@Mock
lateinit var categoryRepository: CategoryRepository

@Mock
lateinit var itemMapper: ItemMapper

@InjectMocks
lateinit var itemService: ItemService


    @Test
    fun `아이템 단건 조회 성공`(){
        // Given
        val itemId: Long = 1
        val itemViewApiDto = CreateItemDto.itemViewApiDto("하와이안 셔츠", 1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val findItemTypeEntity = CreateItemTypeEntity.findItemEntity()
        val findItemEntity = CreateItemEntity.findItemEntity(findCategoryEntity, findItemTypeEntity)

        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findItemEntity))
        given(itemMapper.entityToViewApiDto(findItemEntity))
            .willReturn(itemViewApiDto)

        // When
        val result = itemService.findItem(itemId)

        // Then
        assertEquals(result, itemViewApiDto)
        assertEquals(itemViewApiDto.itemName, "하와이안 셔츠")
        verify(itemRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemMapper).entityToViewApiDto(findItemEntity)
    }

    @Test
    fun `아이템 단건 조회 실패(존재하지 않는 아이템)`(){
        // Given
        val itemId: Long = 1

        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(ItemException::class.java) {
            itemService.findItem(itemId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ITEM.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ITEM.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ITEM.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `아이템 다건 조회 성공`(){
        // Given
        val itemId: Long = 1
        val itemViewApiDtoList = CreateItemDto.itemViewApiDtoList()
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val findItemTypeEntity = CreateItemTypeEntity.findItemEntity()
        val findItemEntityList = CreateItemEntity.findItemEntityList(findCategoryEntity, findItemTypeEntity)

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategoryEntity))
        given(itemRepository.findByCategoryAndDeleteFlag(findCategoryEntity, FlagYn.N))
            .willReturn(findItemEntityList)
        given(itemMapper.entityListToViewApiDtoList(findItemEntityList))
            .willReturn(itemViewApiDtoList)

        // When
        val result = itemService.findAllItem(itemId)

        // Then
        assertEquals(result, itemViewApiDtoList)
        assertEquals(result.size, 3)
        assertEquals(result.get(0).itemName, "하와이안 셔츠")
        assertEquals(result.get(1).itemName, "벚꽃 남방")
        assertEquals(result.get(2).itemName, "청자켓")
        verify(categoryRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemRepository).findByCategoryAndDeleteFlag(findCategoryEntity, FlagYn.N)
        verify(itemMapper).entityListToViewApiDtoList(findItemEntityList)
    }

    @Test
    fun `아이템등록 성공`(){
        // Given
        val itemSaveDto = CreateItemDto.itemSaveDto()
        val itemViewApiDto = CreateItemDto.itemViewApiDto("하와이안 셔츠", 1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val findItemTypeEntity = CreateItemTypeEntity.findItemEntity()
        val savedItemEntity = CreateItemEntity.savedItemEntity(itemSaveDto, findCategoryEntity, findItemTypeEntity)

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategoryEntity))
        given(itemTypeRepository.findByTypeCodeAndDeleteFlag(itemSaveDto.typeCode, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))
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
        verify(itemTypeRepository).findByTypeCodeAndDeleteFlag(itemSaveDto.typeCode, FlagYn.N)
        verify(itemRepository).save(any(ItemEntity::class.java))
    }

    @Test
    fun `아이템수정 성공`(){
        // Given
        val itemId: Long = 1
        val itemUpdateDto = CreateItemDto.itemUpdateDto()
        val itemViewApiDto = CreateItemDto.itemViewApiDto("스카쟌 점퍼", 1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val findItemTypeEntity = CreateItemTypeEntity.findItemEntity()
        val findItemEntity = CreateItemEntity.findItemEntity(findCategoryEntity, findItemTypeEntity)

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategoryEntity))
        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findItemEntity))
        findItemEntity.update(itemUpdateDto, findCategoryEntity)
        given(itemMapper.entityToViewApiDto(findItemEntity))
            .willReturn(itemViewApiDto)

        // When
        val result = itemService.updateItem(itemId, itemUpdateDto)

        // Then
        assertEquals(result, itemViewApiDto)
        assertEquals(itemViewApiDto.itemName, "스카쟌 점퍼")
        verify(categoryRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemMapper).entityToViewApiDto(findItemEntity)
    }

    @Test
    fun `아이템수정 실패(존재하지 않는 카테고리)`(){
        // Given
        val itemId: Long = 1
        val itemUpdateDto = CreateItemDto.itemUpdateDto()
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(CategoryException::class.java) {
            itemService.updateItem(itemId, itemUpdateDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_CATEGORY.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_CATEGORY.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ITEM.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `아이템수정 실패(존재하지 않는 아이템)`(){
        // Given
        val itemId: Long = 1
        val itemUpdateDto = CreateItemDto.itemUpdateDto()
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategoryEntity))
        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(ItemException::class.java) {
            itemService.updateItem(itemId, itemUpdateDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ITEM.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ITEM.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ITEM.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `아이템 삭제 실패(존재하지 않는 아이템)`(){
        // Given
        val itemId: Long = 1

        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(ItemException::class.java) {
            itemService.deleteItem(itemId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_ITEM.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_ITEM.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_ITEM.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `아이템 삭제 성공`(){
        // Given
        val itemId: Long = 1
        val findCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val findItemTypeEntity = CreateItemTypeEntity.findItemEntity()
        val findItemEntity = CreateItemEntity.findItemEntity(findCategoryEntity, findItemTypeEntity)


        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findItemEntity))


        // When
        itemService.deleteItem(itemId)

        // Then
        verify(itemRepository).findByIdAndDeleteFlag(1, FlagYn.N)
    }
}