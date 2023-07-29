package org.khw.kotlinspring.user.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.CategorySaveDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.user.domain.dto.UserApiDto
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.khw.kotlinspring.user.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserRestController(val userService: UserService) {

    @PostMapping("")
    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 등록.")
    fun saveCategory(@RequestBody @Valid userSaveDto: UserSaveDto) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(userService.userSave(userSaveDto))
    }
}