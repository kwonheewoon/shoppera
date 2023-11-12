package org.khw.shoppera.user.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.khw.shoppera.common.response.ErrCommonResponse
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
    @Operation(summary = "회원 조회", description = "회원 정보를 조회.", responses = [
        ApiResponse(
            responseCode = "200_103",
            description = "회원 정보가 정상 적으로 조회 되었습니다.",
            content = [Content(schema = Schema(implementation = UserApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun findUser(@PathVariable accountId: String) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
                .body(CommonResponse(ResCode.USERS_FIND, userService.findUser(accountId)))
    }

    @PostMapping("")
    @Operation(summary = "회원 등록", description = "회원 정보를 등록.", responses = [
        ApiResponse(
            responseCode = "200_100",
            description = "회원 정보가 등록 되었습니다.",
            content = [Content(schema = Schema(implementation = UserApiDto::class))]
        ),
        ApiResponse(
            responseCode = "409_002",
            description = "중복된 계정입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun userSave(@RequestBody @Valid userSaveDto: UserSaveDto) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_SAVE, userService.userSave(userSaveDto)))
    }

    @PutMapping("/{userId}")
    @Operation(summary = "회원 수정", description = "회원 정보를 수정.", responses = [
        ApiResponse(
            responseCode = "200_101",
            description = "회원 정보가 수정 되었습니다.",
            content = [Content(schema = Schema(implementation = UserApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun userUpdate(@PathVariable userId: Long, @RequestBody @Valid userUpdateDto: UserUpdateDto) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_UPDATE, userService.userUpdate(userId, userUpdateDto)))
    }

    @PutMapping("/{userId}/password")
    @Operation(summary = "회원 패스워드 변경", description = "회원 패스워드를 변경.", responses = [
        ApiResponse(
            responseCode = "200_104",
            description = "패스워드 변경이 완료 되었습니다.",
            content = [Content(schema = Schema(implementation = UserApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun userPasswordUpdate(@PathVariable userId: Long, @RequestParam changePassword: String) : ResponseEntity<CommonResponse<UserApiDto>> {
            userService.userPasswordUpdate(userId, changePassword)
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_PASSWORD_UPDATE))
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "회원 삭제", description = "회원 정보를 삭제.", responses = [
        ApiResponse(
            responseCode = "200_102",
            description = "회원 정보가 삭제 되었습니다.",
            content = [Content(schema = Schema(implementation = UserApiDto::class))]
        ),
        ApiResponse(
            responseCode = "404_003",
            description = "잘못된 회원 정보 입니다.",
            content = [Content(schema = Schema(implementation = ErrCommonResponse::class))]
        )
    ])
    fun userDelete(@PathVariable userId: Long) : ResponseEntity<CommonResponse<UserApiDto>> {
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.USERS_DELETE, userService.userDelete(userId)))
    }
}