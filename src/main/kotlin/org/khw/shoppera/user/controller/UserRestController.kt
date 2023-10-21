package org.khw.shoppera.user.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.user.domain.dto.UserApiDto
import org.khw.shoppera.user.domain.dto.UserSaveDto
import org.khw.shoppera.user.domain.dto.UserUpdateDto
import org.khw.shoppera.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserRestController(val userService: UserService) {

    @GetMapping("/{accountId}")
    @Operation(summary = "회원 조회", description = "회원 정보를 조회.")
    fun findUser(@PathVariable accountId: String) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
                .body(CommonResponse(ResCode.USERS_FIND, userService.findUser(accountId)))
    }

    @PostMapping("")
    @Operation(summary = "회원 등록", description = "회원 정보를 등록.")
    fun userSave(@RequestBody @Valid userSaveDto: UserSaveDto) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_SAVE, userService.userSave(userSaveDto)))
    }

    @PutMapping("/{userId}")
    @Operation(summary = "회원 수정", description = "회원 정보를 수정.")
    fun userUpdate(@PathVariable userId: Long, @RequestBody @Valid userUpdateDto: UserUpdateDto) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_UPDATE, userService.userUpdate(userId, userUpdateDto)))
    }

    @PutMapping("/{userId}/password")
    @Operation(summary = "회원 수정", description = "회원 패스워드를 변경.")
    fun userPasswordUpdate(@PathVariable userId: Long, @RequestParam changePassword: String) : ResponseEntity<CommonResponse<UserApiDto>> {
            userService.userPasswordUpdate(userId, changePassword)
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_PASSWORD_UPDATE))
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "회원 삭제", description = "회원 정보를 삭제.")
    fun userDelete(@PathVariable userId: Long) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_DELETE, userService.userDelete(userId)))
    }
}