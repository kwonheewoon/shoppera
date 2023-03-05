package org.khw.kotlinspring.category.domain.dto

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.Comment


@Builder
class CategoryDto(

    id : Long,

    categoryNm : String,

    depth : Int,

    orderNo : Int,

    deleteFlag : String
) {
    val id = id

    var categoryNm = categoryNm

    var depth = depth

    var orderNo = orderNo

    var deleteFlag = deleteFlag

}