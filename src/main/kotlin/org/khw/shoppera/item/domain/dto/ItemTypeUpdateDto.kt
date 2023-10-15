package org.khw.shoppera.item.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.khw.shoppera.common.enums.CommonEnum

data class ItemTypeUpdateDto (
    @Schema(description = "타입 코드", example = "ELEC")
    val typeCode: String,

    @Schema(description = "타입 명", example = "전자기기")
    val typeName: String,
)