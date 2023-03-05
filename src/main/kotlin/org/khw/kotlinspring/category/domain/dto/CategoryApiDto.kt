package org.khw.kotlinspring.category.domain.dto

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.Comment


@Builder
class CategoryApiDto(

    val id : Long? = null,

    categoryNm : String,

    depth : Int,

    orderNo : Int,

    deleteFlag : String
) {

    var categoryNm = categoryNm

    var depth = depth

    var orderNo = orderNo

    var deleteFlag = deleteFlag

}