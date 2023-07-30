package org.khw.kotlinspring.user.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.category.domain.dto.CategorySaveDto
import org.khw.kotlinspring.category.domain.dto.CategoryViewApiDto
import org.khw.kotlinspring.user.domain.dto.UserApiDto
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.dto.UserUpdateDto
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.khw.kotlinspring.user.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserRestController(val userService: UserService) {

    @PostMapping("")
    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 등록.")
    fun userSave(@RequestBody @Valid userSaveDto: UserSaveDto) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(userService.userSave(userSaveDto))
    }

    @PutMapping("/{userId}")
    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 등록.")
    fun userUpdate(@PathVariable userId: Long, @RequestBody @Valid userUpdateDto: UserUpdateDto) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(userService.userUpdate(userId, userUpdateDto))
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "카테고리 등록", description = "카테고리 정보를 등록.")
    fun userDelete(@PathVariable userId: Long) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .headers(HttpHeaders())
            .body(userService.userDelete(userId))
    }
}