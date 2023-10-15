package org.khw.shoppera.brand.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.shoppera.common.enums.CommonEnum.FlagYn

data class BrandViewApiDto(

    @Schema(description = "아이디", example = "1")
    val id: Long,

    @Schema(description = "브랜드 이름", example = "Chanel")
    val name: String,

    @Schema(description = "브랜드 설명", example = "샤넬 브랜드입니다.")
    val explanation: String,

    @Schema(description = "브랜드 설립 년", example = "1924")
    val foundedYear: Int,

    @Schema(description = "브랜드 표출 여부", example = "Y")
    val displayFlag: FlagYn
)