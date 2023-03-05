package org.khw.kotlinspring.category.domain.dto

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.Comment
import java.util.*


@Builder
class CategoryViewApiDto(

    val id : Long,

    val categoryNm : String,

    val depth : Int,

    val orderNo : Int,

    val deleteFlag : String,

    val parentCategory : CategoryViewApiDto?,

    var childCategoryList : List<CategoryViewApiDto>? = ArrayList<CategoryViewApiDto>()
) {



}