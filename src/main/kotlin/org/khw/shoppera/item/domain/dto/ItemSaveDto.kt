package org.khw.shoppera.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.itemoption.domain.dto.ItemOptionSaveDto

data class ItemSaveDto (

    @Schema(description = "아이템 타입 코드", example = "ELEC")
    val typeCode: String,

    @Schema(description = "아이템 이름", example = "하와이안 셔츠")
    val itemName: String,

    @Schema(description = "가격", example = "24500")
    val price: Int,

    @Schema(description = "브랜드 아이디", example = "1")
    val brandId: Long,

    @Schema(description = "카테고리 아이디", example = "1")
    val categoryId: Long,

    @Schema(description = "아이템 옵션 리스트", example = "{itemId : 2, optionName : \"FREE\", orderNo : \"N\"}")
    val itemOptionList: List<ItemOptionSaveDto>,

    @Schema(description = "표출여부", example = "N")
    val displayFlag: FlagYn,

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag: FlagYn
)