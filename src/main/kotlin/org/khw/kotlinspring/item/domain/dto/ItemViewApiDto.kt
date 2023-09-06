package org.khw.kotlinspring.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.enums.CommonEnum

class ItemViewApiDto (
    id: Long,

    typeName: String,

    itemName: String,

    price: Int,

    categoryId: Long,

    displayFlag: CommonEnum.FlagYn,

    deleteFlag: CommonEnum.FlagYn
){
    @Schema(description = "아이템 아이디", example = "23")
    val id = id

    @Schema(description = "아이템 타입명", example = "의류")
    val typeName = typeName

    @Schema(description = "아이템 이름", example = "하와이안 셔츠")
    val itemName = itemName

    @Schema(description = "가격", example = "65000")
    val price = price

    @Schema(description = "카테고리 아이디", example = "1")
    val categoryId = categoryId

    @Schema(description = "표출여부", example = "N")
    val displayFlag = displayFlag

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag = deleteFlag
}