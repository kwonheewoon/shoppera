package org.khw.kotlinspring.category.domain.dto

import lombok.*
import org.khw.kotlinspring.common.enums.CommonEnum.*
import java.util.*


@Builder
class CategoryViewApiDto(

    val id : Long,

    val categoryNm : String,

    val depth : Int,

    val orderNo : Int,

    val deleteFlag : FlagYn,

    val parentCategory : CategoryViewApiDto?,

    var childCategorys : List<CategoryViewApiDto>? = ArrayList<CategoryViewApiDto>()
) {



}