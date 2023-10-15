package org.khw.shoppera.category.domain.dto

import lombok.*
import org.khw.shoppera.common.enums.CommonEnum.FlagYn


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