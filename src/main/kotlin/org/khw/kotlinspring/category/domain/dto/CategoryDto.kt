package org.khw.kotlinspring.category.domain.dto

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.Comment
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn


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