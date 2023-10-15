package org.khw.shoppera.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.shoppera.common.enums.CommonEnum.*

class ItemTypeViewApiDto (

    @Schema(description = "아이디", example = "1")
    val id: Long,

    @Schema(description = "타입 코드", example = "ELEC")
    val typeCode: String,

    @Schema(description = "타입 명", example = "전자기기")
    val typeName: String,

    @Schema(description = "삭제여부", example = "N")
    val deleteFlag: FlagYn
)