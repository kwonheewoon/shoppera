package org.khw.shoppera.category.domain.dto

import lombok.*
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import java.util.ArrayList


@Builder
class CategoryApiDto(

    val id : Long,

    val categoryNm : String,

    val depth : Int,

    val orderNo : Int,

    val deleteFlag : FlagYn,

    val parentCategory : CategoryViewApiDto?,

    var childCategorys : List<CategoryApiDto>? = ArrayList<CategoryApiDto>()
)