package org.khw.kotlinspring.category.domain.entity

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.category.domain.dto.CategoryDto

@Entity
@Table(name = "category")
@Builder
@DynamicInsert
@DynamicUpdate
class CategoryEntity(
    categoryNm : String,
    depth : Integer,
    orderNo : Integer,
    deleteFlag : String
) {
    @Id @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("카테고리 아이디")
    val id : Long? = null

    @Column(name = "category_nm", nullable = false)
    @Comment("카테고리 이름")
    var categoryNm = categoryNm
        protected set

    @Column(name = "depth", nullable = false)
    @Comment("카테고리 깊이")
    var depth = depth
        protected set

    @Column(name = "order_no", nullable = false)
    @Comment("카테고리 이름")
    var orderNo = orderNo
        protected set

    @Column(name = "delete_flag", nullable = false)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        protected set

    fun modify(categoryDto: CategoryDto){
        this.categoryNm = categoryDto.categoryNm
        this.depth = categoryDto.depth
        this.orderNo = categoryDto.orderNo
    }

}