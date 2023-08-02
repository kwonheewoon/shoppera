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

    @GetMapping("/{accountId}")
    @Operation(summary = "회원 조회", description = "회원 정보를 조회.")
    fun findUser(@PathVariable accountId: String) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
                .body(userService.findUser(accountId))
    }

    @PostMapping("")
    @Operation(summary = "회원 등록", description = "회원 정보를 등록.")
    fun userSave(@RequestBody @Valid userSaveDto: UserSaveDto) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .body(userService.userSave(userSaveDto))
    }

    @PutMapping("/{userId}")
    @Operation(summary = "회원 수정", description = "회원 정보를 수정.")
    fun userUpdate(@PathVariable userId: Long, @RequestBody @Valid userUpdateDto: UserUpdateDto) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .body(userService.userUpdate(userId, userUpdateDto))
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "회원 삭제", description = "회원 정보를 삭제.")
    fun userDelete(@PathVariable userId: Long) : ResponseEntity<UserApiDto> {
        return ResponseEntity.ok()
            .body(userService.userDelete(userId))
    }
}