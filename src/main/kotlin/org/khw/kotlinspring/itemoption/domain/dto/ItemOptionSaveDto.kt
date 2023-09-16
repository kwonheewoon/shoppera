package org.khw.kotlinspring.itemoption.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn

data class ItemOptionSaveDto (

    @Schema(description = "옵션 이름", example = "FREE")
    val optionName: String,

    @Schema(description = "옵션 순번", example = "1")
    val orderNo: Int,

    @Schema(description = "표출여부", example = "N")
    val displayFlag: FlagYn
)