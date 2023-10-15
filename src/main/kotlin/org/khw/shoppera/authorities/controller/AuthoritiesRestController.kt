package org.khw.shoppera.authorities.controller

import lombok.RequiredArgsConstructor
import org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.shoppera.authorities.domain.dto.AuthoritiesViewApiDto
import org.khw.shoppera.authorities.service.AuthoritiesService
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.response.CommonResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
class AuthoritiesRestController(val authoritiesService: AuthoritiesService) {

    @PostMapping
    fun saveAuthorities(@RequestBody authoritiesSaveDto: org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto): ResponseEntity<CommonResponse<AuthoritiesViewApiDto>>{
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.AUTHORITIES_SAVE, authoritiesService.saveAuthorities(authoritiesSaveDto)))
    }

    @DeleteMapping("/{authoritiesId}/users/{userId}")
    fun deleteAuthorities(@PathVariable authoritiesId: Long, @PathVariable userId: Long): ResponseEntity<CommonResponse<Unit>>{
        authoritiesService.deleteAuthorities(authoritiesId, userId)
        return ResponseEntity.ok()
            .body(CommonResponse(ResCode.AUTHORITIES_DELETE))
    }
}