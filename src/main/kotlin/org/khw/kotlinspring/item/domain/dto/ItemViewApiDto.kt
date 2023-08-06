package org.khw.kotlinspring.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.CommonEnum

class ItemViewApiDto (
    id: Long,

    itemName: String,

    cateogryId: Long,

    displayFlag: CommonEnum.FlagYn,

    deleteFlag: CommonEnum.FlagYn
){
    @Schema(description = "아이템 아이디", example = "23")
    val id = id

    @Schema(description = "아이템 이름", example = "하와이안 셔츠")
    val itemName = itemName

    @Schema(description = "카테고리 아이디", example = "1")
    val cateogryId = cateogryId

    @Schema(description = "표출여부", example = "N")
    val displayFlag = displayFlag

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag = deleteFlag
}