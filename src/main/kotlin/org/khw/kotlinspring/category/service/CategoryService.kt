package org.khw.kotlinspring.category.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.CategoryApiDto
import org.khw.kotlinspring.category.domain.dto.CategoryDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.category.domain.mapper.CategoryMapper
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CategoryService(val categoryRepository: CategoryRepository,
val categoryMapper: CategoryMapper) {

    fun findAllCategory() : List<CategoryViewApiDto>{
        return categoryMapper.entityListToViewApiDtoList(categoryRepository.findAll())
    }

    fun saveCategory(categoryDto: CategoryDto) : CategoryViewApiDto {
        return categoryMapper.entityToViewApiDto(categoryRepository.save(CategoryEntity(categoryDto.categoryNm, categoryDto.depth, categoryDto.orderNo, categoryDto.deleteFlag)))
    }
}