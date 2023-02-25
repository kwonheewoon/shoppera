package org.khw.kotlinspring.category.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.CategoryApiDto
import org.khw.kotlinspring.category.domain.dto.CategoryDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.category.domain.mapper.CategoryMapper
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.util.Optional

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

    fun modifyCategory(categoryDto: CategoryDto) : CategoryViewApiDto {
        val findCategoryEntity : CategoryEntity = categoryRepository.findById(categoryDto.id).orElseThrow{throw RuntimeException("메뉴 정보가 존재하지 않습니다.")}
        findCategoryEntity.modify(categoryDto)
        return categoryMapper.entityToViewApiDto(categoryRepository.save(findCategoryEntity))
    }
}