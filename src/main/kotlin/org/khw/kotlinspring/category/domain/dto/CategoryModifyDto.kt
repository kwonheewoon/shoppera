package org.khw.kotlinspring.category.domain.dto

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.Comment

@Schema(description = "카테고리 저장시 사용될 DTO")
@Builder
class CategoryModifyDto(

    id: Long,

    parentId: Long?,

    categoryNm : String,

    depth : Int,

    orderNo : Int
) {

    @Schema(description = "수정될 카테고리 아이디", example = "1")
    val id = id

    @Schema(description = "부모 카테고리 아이디", example = "1")
    val parentId = parentId

    @Schema(description = "상의/바지 or 아우터", example = "상의", required = true)
    val categoryNm = categoryNm

    @Schema(description = "카테고리 깊이", example = "1", required = true)
    val depth = depth

    @Schema(description = "카테고리 정렬순서", example = "1", required = true)
    val orderNo = orderNo

}