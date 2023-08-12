package org.khw.kotlinspring.category.domain.dto

import lombok.*
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn


@Builder
class CategoryDto(

    id : Long,

    categoryNm : String,

    depth : Int,

    orderNo : Int,

    deleteFlag : FlagYn
) {
    val id = id

    var categoryNm = categoryNm

    var depth = depth

    var orderNo = orderNo

    var deleteFlag = deleteFlag

}