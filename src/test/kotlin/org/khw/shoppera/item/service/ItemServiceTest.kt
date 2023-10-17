package org.khw.shoppera.item.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.category.repository.CategoryRepository

import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.category.factory.CreateCategoryEntity
import org.khw.shoppera.item.factory.CreateItemDto
import org.khw.shoppera.item.factory.CreateItemEntity
import org.khw.shoppera.item.domain.mapper.ItemMapper
import org.khw.shoppera.item.repository.ItemRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.util.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.khw.shoppera.brand.factory.CreateBrandEntity
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException
import org.khw.shoppera.common.exception.CategoryException
import org.khw.shoppera.common.exception.ItemException
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.item.factory.CreateItemTypeEntity
import org.khw.shoppera.item.repository.ItemTypeRepository
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
lateinit var brandRepository: BrandRepository

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
        val itemViewApiDto = CreateItemDto.itemViewApiDto("의류", 65000, "하와이안 셔츠", 1,1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        val findBrand = CreateBrandEntity.findBrand()
        val findCategory = CreateCategoryEntity.categoryEntityParent()
        val findItemType = CreateItemTypeEntity.findItemTypeEntity()
        val findItem = CreateItemEntity.findItemEntity(findBrand, findCategory, findItemType)

        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findItem))
        given(itemMapper.entityToViewApiDto(findItem))
            .willReturn(itemViewApiDto)

        // When
        val result = itemService.findItem(itemId)

        // Then
        assertEquals(result, itemViewApiDto)
        assertEquals(itemViewApiDto.itemName, "하와이안 셔츠")
        verify(itemRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemMapper).entityToViewApiDto(findItem)
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
        val findBrand = CreateBrandEntity.findBrand()
        val findCategory = CreateCategoryEntity.categoryEntityParent()
        val findItemType = CreateItemTypeEntity.findItemTypeEntity()
        val findItemList = CreateItemEntity.findItemEntityList(findBrand, findCategory, findItemType)

        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategory))
        given(itemRepository.findByCategoryAndDeleteFlag(findCategory, FlagYn.N))
            .willReturn(findItemList)
        given(itemMapper.entityListToViewApiDtoList(findItemList))
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
        verify(itemRepository).findByCategoryAndDeleteFlag(findCategory, FlagYn.N)
        verify(itemMapper).entityListToViewApiDtoList(findItemList)
    }

    @Test
    fun `아이템등록 실패(존재하지 않는 브랜드)`(){
        // Given
        val itemSaveDto = CreateItemDto.itemSaveDto()

        given(brandRepository.findByIdAndDeleteFlag(itemSaveDto.brandId, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(BrandException::class.java) {
            itemService.saveItem(itemSaveDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_BRAND.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_BRAND.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_BRAND.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `아이템등록 성공`(){
        // Given
        val itemSaveDto = CreateItemDto.itemSaveDto()
        val itemViewApiDto = CreateItemDto.itemViewApiDto("의류", 65000,"하와이안 셔츠", 1,1, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        val findBrand = CreateBrandEntity.findBrand()
        val findCategory = CreateCategoryEntity.categoryEntityParent()
        val findItemType = CreateItemTypeEntity.findItemTypeEntity()
        val savedItem = CreateItemEntity.savedItemEntity(itemSaveDto, findBrand, findCategory, findItemType)

        given(brandRepository.findByIdAndDeleteFlag(itemSaveDto.brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(categoryRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findCategory))
        given(itemTypeRepository.findByTypeCodeAndDeleteFlag(itemSaveDto.typeCode, FlagYn.N))
            .willReturn(Optional.of(findItemType))
        given(itemRepository.save(any(Item::class.java)))
            .willReturn(savedItem)
        given(itemMapper.entityToViewApiDto(savedItem))
            .willReturn(itemViewApiDto)

        // When
        val result = itemService.saveItem(itemSaveDto)

        // Then
        assertEquals(result, itemViewApiDto)
        assertEquals(itemViewApiDto.itemName, "하와이안 셔츠")
        assertEquals(itemViewApiDto.brandId, 1)
        verify(brandRepository).findByIdAndDeleteFlag(itemSaveDto.brandId, FlagYn.N)
        verify(categoryRepository).findByIdAndDeleteFlag(itemSaveDto.categoryId, FlagYn.N)
        verify(itemTypeRepository).findByTypeCodeAndDeleteFlag(itemSaveDto.typeCode, FlagYn.N)
        verify(itemRepository).save(any(Item::class.java))
    }

    @Test
    fun `아이템수정 성공`(){
        // Given
        val itemId: Long = 1
        val itemUpdateDto = CreateItemDto.itemUpdateDto()
        val itemViewApiDto = CreateItemDto.itemViewApiDto("의류", 75000,"스카쟌 점퍼", 2,2, displayFlag = FlagYn.N, deleteFlag = FlagYn.N)
        val findBrand = CreateBrandEntity.findBrand()
        val findCategory = CreateCategoryEntity.categoryEntityParent()
        val findItemType = CreateItemTypeEntity.findItemTypeEntity()
        val findItem = CreateItemEntity.findItemEntity(findBrand, findCategory, findItemType)

        given(brandRepository.findByIdAndDeleteFlag(itemUpdateDto.brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(categoryRepository.findByIdAndDeleteFlag(itemUpdateDto.categoryId, FlagYn.N))
            .willReturn(Optional.of(findCategory))
        given(itemTypeRepository.findByTypeCodeAndDeleteFlag(itemUpdateDto.typeCode, FlagYn.N))
            .willReturn(Optional.of(findItemType))
        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findItem))
        findItem.update(itemUpdateDto, findBrand, findCategory, findItemType)
        given(itemMapper.entityToViewApiDto(findItem))
            .willReturn(itemViewApiDto)

        // When
        val result = itemService.updateItem(itemId, itemUpdateDto)

        // Then
        assertEquals(result, itemViewApiDto)
        assertEquals(itemViewApiDto.itemName, "스카쟌 점퍼")
        assertEquals(result.brandId, 2)
        assertEquals(result.categoryId, 2)
        verify(brandRepository).findByIdAndDeleteFlag(itemUpdateDto.brandId, FlagYn.N)
        verify(categoryRepository).findByIdAndDeleteFlag(itemUpdateDto.categoryId, FlagYn.N)
        verify(itemRepository).findByIdAndDeleteFlag(1, FlagYn.N)
        verify(itemMapper).entityToViewApiDto(findItem)
    }

    @Test
    fun `아이템수정 실패(존재하지 않는 카테고리)`(){
        // Given
        val itemId: Long = 1
        val itemUpdateDto = CreateItemDto.itemUpdateDto()
        val findBrand = CreateBrandEntity.findBrand()

        given(brandRepository.findByIdAndDeleteFlag(itemUpdateDto.brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(categoryRepository.findByIdAndDeleteFlag(itemUpdateDto.categoryId, FlagYn.N))
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
        val findItemTypeEntity = CreateItemTypeEntity.findItemTypeEntity()
        val findBrand = CreateBrandEntity.findBrand()

        given(brandRepository.findByIdAndDeleteFlag(itemUpdateDto.brandId, FlagYn.N))
            .willReturn(Optional.of(findBrand))
        given(categoryRepository.findByIdAndDeleteFlag(itemUpdateDto.categoryId, FlagYn.N))
            .willReturn(Optional.of(findCategoryEntity))
        given(itemTypeRepository.findByTypeCodeAndDeleteFlag(itemUpdateDto.typeCode, FlagYn.N))
            .willReturn(Optional.of(findItemTypeEntity))
        given(itemRepository.findByIdAndDeleteFlag(itemId, FlagYn.N))
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
        val findBrand = CreateBrandEntity.findBrand()
        val findCategory = CreateCategoryEntity.categoryEntityParent()
        val findItemType = CreateItemTypeEntity.findItemTypeEntity()
        val findItem = CreateItemEntity.findItemEntity(findBrand, findCategory, findItemType)


        given(itemRepository.findByIdAndDeleteFlag(1, FlagYn.N))
            .willReturn(Optional.of(findItem))


        // When
        itemService.deleteItem(itemId)

        // Then
        verify(itemRepository).findByIdAndDeleteFlag(1, FlagYn.N)
    }
}