package org.khw.kotlinspring.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.kotlinspring.common.enums.CommonEnum.*

data class ItemTypeSaveDto (
    @Schema(description = "타입 코드", example = "ELEC")
    val typeCode: String,

    @Schema(description = "타입 명", example = "전자기기")
    val typeName: String,

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag: FlagYn
)