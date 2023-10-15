package org.khw.shoppera.authorities.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 권한 추가에 사용 되는 DTO")
class AuthoritiesSaveDto(
        @Schema(description = "권한 아이디", example = "1")
        val authorityId: Long,

        @Schema(description = "유저 ID(PK)", example = "45")
        val userId: Long
)