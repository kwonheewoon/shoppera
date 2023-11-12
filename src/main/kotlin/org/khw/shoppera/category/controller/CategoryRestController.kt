package org.khw.shoppera.category.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import lombok.RequiredArgsConstructor
import org.khw.shoppera.category.domain.dto.*
import org.khw.shoppera.category.service.CategoryService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
class CategoryRestController(val categoryService: CategoryService) {

    @GetMapping("")
    @Operation(summary = "카테고리 전체 조회", description = "등록된 카테고리 전체 목록을 조회 한다.")
    fun findAllCategory() : ResponseEntity<List<CategoryListApiDto>> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.findAllCategory())
    }

    @PostMapping("")
    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 등록 한다.")
    fun saveCategory(@RequestBody caregorySaveDto: CategorySaveDto) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.saveCategory(caregorySaveDto))
    }

    @PutMapping("")
    @Operation(summary = "카테고리 수정", description = "카테고리 정보를 수정 한다.")
    fun modifyCategory(@RequestBody categoryModifyDto:  CategoryModifyDto) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.modifyCategory(categoryModifyDto))
    }

    @DeleteMapping("{categoryId}")
    @Operation(summary = "카테고리 삭제", description = "카테고리 정보를 삭제 한다.")
    fun deleteCategory(@Parameter(description = "삭제될 카테고리 ID") @PathVariable categoryId:Long) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.deleteCategory(categoryId))
    }
}