package org.khw.shoppera.authorities.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 권한 상세 정보 표출 DTO")
class AuthoritiesViewApiDto(

        @Schema(description = "권한 아이디", example = "1")
        val id: Long,

        @Schema(description = "권한 정보 아이디", example = "1")
        val authorityId: Long,

        @Schema(description = "유저 ID(PK)", example = "45")
        val userId: Long
)