package org.khw.shoppera.category.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.category.domain.mapper.CategoryMapper
import org.khw.shoppera.category.repository.CategoryQueryRepository
import org.khw.shoppera.category.repository.CategoryRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.category.factory.CreateCategoryDto
import org.khw.shoppera.category.factory.CreateCategoryEntity
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CategoryServiceTest {

    @Mock
    lateinit var categoryMapper: CategoryMapper

    @Mock
    lateinit var categoryRepository: CategoryRepository

    @Mock
    lateinit var categoryQueryRepository: CategoryQueryRepository

    @InjectMocks
    lateinit var categoryService: CategoryService

    @Test
    fun `카테고리 조회(자식 카테고리 포함)`() {
        // Given
        val findCategoryEntityList = CreateCategoryEntity.categoryEntityChildExistList()
        val categoryListApidto = CreateCategoryDto.categoryListApiDtoParentAndChildExist()

        given(categoryRepository.findByDepthAndDeleteFlag(1, FlagYn.N))
            .willReturn(findCategoryEntityList)
        given(categoryMapper.entityListToListApiDtoList(findCategoryEntityList))
            .willReturn(categoryListApidto)

        // When
        val result = categoryService.findAllCategory()

        // Then
        assertEquals(categoryListApidto, result)
        assertEquals(result[0].childCategorys?.size,3 )
        assertEquals(result[0].childCategorys!![0].categoryNm, "자식 카테고리1")
        verify(categoryRepository).findByDepthAndDeleteFlag(1, FlagYn.N)
        verify(categoryMapper).entityListToListApiDtoList(findCategoryEntityList)

    }

    @Test
    fun `카테고리 등록 성공(부모 카테고리)`(){
        // Given
        val categorySaveDto = CreateCategoryDto.categorySaveDtoParentNonExist()
        val categorySavedEntity = CreateCategoryEntity.categoryEntityParentNonExist()
        val categoryViewApiDto = CreateCategoryDto.categoryViewApiDtoParentAndChildNonExist()

        given(categoryRepository.save(any(Category::class.java)))
                .willReturn(categorySavedEntity)
        given(categoryMapper.entityToViewApiDto(categorySavedEntity))
                .willReturn(categoryViewApiDto)

        // When
        val result = categoryService.saveCategory(categorySaveDto)

        // Then
        assertEquals(categoryViewApiDto, result)
        verify(categoryRepository).save(any(Category::class.java))
        verify(categoryMapper).entityToViewApiDto(categorySavedEntity)
    }

    @Test
    fun `카테고리 등록 성공(자식 카테고리)`(){
        // Given
        val categorySaveDto = CreateCategoryDto.categorySaveDtoParentExist()
        val findParentCategoryEntity = CreateCategoryEntity.categoryEntityParent()
        val categorySavedEntity = CreateCategoryEntity.categoryEntityParentExist()
        val categoryViewApiDto = CreateCategoryDto.categoryViewApiDtoParentAndChildExist()

        given(categoryRepository.findById(categorySaveDto.parentId!!))
            .willReturn(Optional.of(findParentCategoryEntity))
        given(categoryRepository.save(any(Category::class.java)))
            .willReturn(categorySavedEntity)
        given(categoryMapper.entityToViewApiDto(categorySavedEntity))
            .willReturn(categoryViewApiDto)

        // When
        val result = categoryService.saveCategory(categorySaveDto)

        // Then
        assertEquals(categoryViewApiDto, result)
        verify(categoryRepository).findById(categorySaveDto.parentId!!)
        verify(categoryRepository).save(any(Category::class.java))
        verify(categoryMapper).entityToViewApiDto(categorySavedEntity)
    }

    @Test
    fun `카테고리 등록 실패(자식 카테고리) 부모카테고리 미존재`(){
        // Given
        val categorySaveDto = CreateCategoryDto.categorySaveDtoParentExist()


        given(categoryRepository.findById(categorySaveDto.parentId!!))
            .willReturn(Optional.empty())

        // When & Then
        assertThrows(IllegalStateException::class.java) {
            categoryService.saveCategory(categorySaveDto)
        }
    }
}