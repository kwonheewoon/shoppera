package org.khw.shoppera.category.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.category.domain.dto.*
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.category.domain.entity.CategoryEntityFactory
import org.khw.shoppera.category.domain.mapper.CategoryMapper
import org.khw.shoppera.category.repository.CategoryQueryRepository
import org.khw.shoppera.category.repository.CategoryRepository
import org.khw.shoppera.common.enums.CommonEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class CategoryService(val categoryRepository: CategoryRepository,
                      val categoryMapper: CategoryMapper,
                      val categoryQueryRepository: CategoryQueryRepository) {

    @Transactional(readOnly = true)
    fun findAllCategory() : List<CategoryListApiDto> {
        var caregoryList = categoryRepository.findByDepthAndDeleteFlag(1, CommonEnum.FlagYn.N)

        //자식 카테고리 재귀 조회 호출
        caregoryList.forEach { category ->
            loadAllChildren(category)
        }
        return categoryMapper.entityListToListApiDtoList(caregoryList)
    }

    /**
     * 1DEPTH 카테고리 기준 모든 자식 카테고리 재귀 조회
     *
     * @param category 카테고리 엔티티
     * @return Unit
     */
    private fun loadAllChildren(category: Category) {
        val children = category.childCategoryList
        children.forEach { child ->
            loadAllChildren(child)
        }
    }

    @Transactional
    fun saveCategory(categorySaveDto: CategorySaveDto) : CategoryViewApiDto {

        //부모 카테고리 아이디가 존재할시
        if(categorySaveDto.parentId != null){
            //부모카테고리 조회
            val parentCategoryEntity = categoryRepository.findById(categorySaveDto.parentId).orElseThrow{throw IllegalStateException("부모 카테고리가 존재하지 않습니다.")}
            return categoryMapper.entityToViewApiDto(categoryRepository.save(CategoryEntityFactory.createCategoryEntityParentExist(categorySaveDto, parentCategoryEntity)))
        }
        //부모 카테고리 아이디가 미 존재할시
        return categoryMapper.entityToViewApiDto(categoryRepository.save(CategoryEntityFactory.createCategoryEntityParentNonExist(categorySaveDto)))
    }

    @Transactional
    fun modifyCategory(categoryModifyDto: CategoryModifyDto) : CategoryViewApiDto {
        val findCategory : Category = categoryRepository.findById(categoryModifyDto.id).orElseThrow{throw RuntimeException("메뉴 정보가 존재하지 않습니다.")}
        findCategory.modify(categoryModifyDto)
        return categoryMapper.entityToViewApiDto(categoryRepository.save(findCategory))
    }

    @Transactional
    fun deleteCategory(categoryId: Long) : CategoryViewApiDto {
        val findCategory : Category = categoryRepository.findById(categoryId).orElseThrow{throw RuntimeException("메뉴 정보가 존재하지 않습니다.")}
        if(categoryQueryRepository.deleteCategory(categoryId) < 1){
            throw RuntimeException("메뉴 삭제에 실패하였습니다.")
        }
        return categoryMapper.entityToViewApiDto(findCategory)
    }
}