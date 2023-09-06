package org.khw.kotlinspring.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.enums.CommonEnum

class ItemUpdateDto (

    typeCode: String,

    itemName: String,

    price: Int,

    categoryId: Long,

    displayFlag: CommonEnum.FlagYn,

    deleteFlag: CommonEnum.FlagYn
){
    @Schema(description = "아이템 타입 코드", example = "ELEC")
    val typeCode = typeCode

    @Schema(description = "아이템 이름", example = "하와이안 셔츠")
    val itemName = itemName

    @Schema(description = "가격", example = "24500")
    val price = price

    @Schema(description = "카테고리 아이디", example = "1")
    val categoryId = categoryId

    @Schema(description = "표출여부", example = "N")
    val displayFlag = displayFlag

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag = deleteFlag
}