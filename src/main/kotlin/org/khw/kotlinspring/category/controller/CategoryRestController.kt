package org.khw.kotlinspring.category.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.*
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.category.service.CategoryService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
class CategoryRestController(val categoryService: CategoryService) {

    @GetMapping("")
    fun findAllCategory() : ResponseEntity<MutableList<() -> CategoryViewApiDto>> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.findAllCategory())
    }

    @PostMapping("")
    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 등록.")
    fun saveCategory(@RequestBody caregorySaveDto: CategorySaveDto) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.saveCategory(caregorySaveDto))
    }

    @PutMapping("")
    fun modifyCategory(@RequestBody categoryModifyDto:  CategoryModifyDto) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.modifyCategory(categoryModifyDto))
    }

    @DeleteMapping("{categoryId}")
    fun deleteCategory(@Parameter(description = "삭제될 카테고리 ID") @PathVariable categoryId:Long) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.deleteCategory(categoryId))
    }
}