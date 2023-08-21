package org.khw.kotlinspring.authorities.controller

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesViewApiDto
import org.khw.kotlinspring.authorities.service.AuthoritiesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
class AuthoritiesController(val authoritiesService: AuthoritiesService) {

    @PostMapping
    fun saveAuthorities(@RequestBody authoritiesSaveDto: AuthoritiesSaveDto): ResponseEntity<AuthoritiesViewApiDto>{
        return ResponseEntity.ok()
            .body(authoritiesService.saveAuthorities(authoritiesSaveDto))
    }
}