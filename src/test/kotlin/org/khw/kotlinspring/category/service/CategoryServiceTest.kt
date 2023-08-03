package org.khw.kotlinspring.category.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.category.domain.entity.CategoryEntityFactory
import org.khw.kotlinspring.category.domain.mapper.CategoryMapper
import org.khw.kotlinspring.category.repository.CategoryQueryRepository
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.common.factory.category.CreateCategoryDto
import org.khw.kotlinspring.common.factory.category.CreateCategoryEntity
import org.khw.kotlinspring.common.factory.user.CreateUserDto
import org.khw.kotlinspring.common.factory.user.CreateUserEntity
import org.khw.kotlinspring.user.mapper.UserMapper
import org.khw.kotlinspring.user.repository.UserRepository
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
    fun `카테고리 등록 성공(부모 카테고리)`(){
        // Given
        val categorySaveDto = CreateCategoryDto.categorySaveDtoParentNonExist()
        val categoryCreateEntity = CategoryEntityFactory.createCategoryEntityParentNonExist(categorySaveDto)
        val categorySavedEntity = CreateCategoryEntity.categoryEntityParentNonExist()
        val categoryViewApiDto = CreateCategoryDto.categoryViewApiDtoParentAndChildNonExist()

        given(categoryRepository.save(any(CategoryEntity::class.java)))
                .willReturn(categorySavedEntity)
        given(categoryMapper.entityToViewApiDto(categorySavedEntity))
                .willReturn(categoryViewApiDto)

        // When
        val result = categoryService.saveCategory(categorySaveDto)

        // Then
        assertEquals(categoryViewApiDto, result)
        verify(categoryRepository).save(any(CategoryEntity::class.java))
        verify(categoryMapper).entityToViewApiDto(categorySavedEntity)
    }
}