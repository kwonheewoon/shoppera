package org.khw.kotlinspring.itemoption.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn

data class ItemOptionViewApiDto (
    @Schema(description = "아이템 옵션 아이디", example = "1")
    val id: Long,

    @Schema(description = "아이템 아이디", example = "2")
    val itemId: Long,

    @Schema(description = "아이템 dlfma", example = "하와이안 셔츠")
    val itemName: String,

    @Schema(description = "옵션 이름", example = "FREE")
    val optionName: String,

    @Schema(description = "옵션 순번", example = "1")
    val orderNo: Int,

    @Schema(description = "표출여부", example = "N")
    val displayFlag: FlagYn,

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag: FlagYn
)