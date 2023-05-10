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
    fun findAllCategory() : List<CategoryViewApiDto> {
//        return categoryRepository.findAll().stream().map { category ->
//            val categoryViewApiDto  = categoryMapper.entityToViewApiDto(category)
//            categoryViewApiDto.childCategoryList = categoryMapper.entityListToViewApiDtoList(category.childCategoryList)
//            categoryViewApiDto
//        }.toList()
        return categoryMapper.entityListToViewApiDtoList(categoryRepository.findAll())
    }

    @Transactional
    fun saveCategory(categorySaveDto: CategorySaveDto) : CategoryViewApiDto {

        //부모 카테고리 아이디가 존재할시
        if(categorySaveDto.parentId != null){
            //부모카테고리 조회
            val parentCategoryEntity = categoryRepository.findById(categorySaveDto.parentId).orElseThrow{throw RuntimeException("부모 카테고리가 존재하지 않습니다.")}
            return categoryMapper.entityToViewApiDto(categoryRepository.save(CategoryEntity(categorySaveDto.categoryNm, categorySaveDto.orderNo, parentCategory = parentCategoryEntity, depth = parentCategoryEntity.depth )))
        }
        //부모 카테고리 아이디가 미 존재할시
        return categoryMapper.entityToViewApiDto(categoryRepository.save(CategoryEntity(categorySaveDto.categoryNm, categorySaveDto.orderNo)))
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