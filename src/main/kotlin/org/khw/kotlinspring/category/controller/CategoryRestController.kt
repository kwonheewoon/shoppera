package org.khw.kotlinspring.category.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.CategoryApiDto
import org.khw.kotlinspring.category.domain.dto.CategoryDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
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
    fun findAllCategory() : ResponseEntity<List<CategoryViewApiDto>>{
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.findAllCategory())
    }

    @PostMapping("")
    fun saveCategory(@RequestBody categoryDto: CategoryDto) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.saveCategory(categoryDto))
    }

    @PutMapping("")
    fun modifyCategory(@RequestBody categoryDto: CategoryDto) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.modifyCategory(categoryDto))
    }

    @DeleteMapping("{categoryId}")
    fun deleteCategory(@PathVariable categoryId:Long) : ResponseEntity<CategoryViewApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(categoryService.deleteCategory(categoryId))
    }
}