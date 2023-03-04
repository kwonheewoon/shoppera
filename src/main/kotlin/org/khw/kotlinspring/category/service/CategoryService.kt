package org.khw.kotlinspring.category.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.*
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.category.domain.mapper.CategoryMapper
import org.khw.kotlinspring.category.repository.CategoryQueryRepository
import org.khw.kotlinspring.category.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class CategoryService(val categoryRepository: CategoryRepository,
                      val categoryMapper: CategoryMapper,
                      val categoryQueryRepository: CategoryQueryRepository) {

    @Transactional(readOnly = true)
    fun findAllCategory() : List<CategoryViewApiDto>{
        return categoryMapper.entityListToViewApiDtoList(categoryRepository.findAll())
    }

    @Transactional
    fun saveCategory(categorySaveDto: CategorySaveDto) : CategoryViewApiDto {
        return categoryMapper.entityToViewApiDto(categoryRepository.save(CategoryEntity(categorySaveDto.categoryNm, categorySaveDto.depth, categorySaveDto.orderNo, categorySaveDto.deleteFlag)))
    }

    @Transactional
    fun modifyCategory(categoryModifyDto: CategoryModifyDto) : CategoryViewApiDto {
        val findCategoryEntity : CategoryEntity = categoryRepository.findById(categoryModifyDto.id).orElseThrow{throw RuntimeException("메뉴 정보가 존재하지 않습니다.")}
        findCategoryEntity.modify(categoryModifyDto)
        return categoryMapper.entityToViewApiDto(categoryRepository.save(findCategoryEntity))
    }

    @Transactional
    fun deleteCategory(categoryId: Long) : CategoryViewApiDto {
        val findCategoryEntity : CategoryEntity = categoryRepository.findById(categoryId).orElseThrow{throw RuntimeException("메뉴 정보가 존재하지 않습니다.")}
        if(categoryQueryRepository.deleteCategory(categoryId) < 1){
            throw RuntimeException("메뉴 삭제에 실패하였습니다.")
        }
        return categoryMapper.entityToViewApiDto(findCategoryEntity)
    }
}