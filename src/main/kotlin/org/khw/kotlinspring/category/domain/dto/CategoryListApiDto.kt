package org.khw.kotlinspring.category.domain.dto

import lombok.*
import org.khw.kotlinspring.common.enums.CommonEnum.*
import java.util.*


@Builder
class CategoryListApiDto(

    val id : Long,

    val categoryNm : String,

    val depth : Int,

    val orderNo : Int,

    val deleteFlag : FlagYn,

    var childCategorys : List<CategoryListApiDto>? = ArrayList<CategoryListApiDto>()
) {



}