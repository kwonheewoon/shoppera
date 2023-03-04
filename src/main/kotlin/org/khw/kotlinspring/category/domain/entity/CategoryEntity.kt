package org.khw.kotlinspring.category.domain.entity

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.category.domain.dto.CategoryModifyDto
import java.util.*

@Entity
@Table(name = "category")
@Builder
@DynamicInsert
@DynamicUpdate
class CategoryEntity(
    categoryNm: String,
    depth: Int,
    orderNo: Int,
    deleteFlag: String,
    parentCategoryEntity: CategoryEntity? = null
) {
    constructor(id: Long) : this("", 0, 0, "") {
        this.id = id
    }

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("카테고리 아이디")
    var id : Long? = null

    @Column(name = "category_nm", nullable = false)
    @Comment("카테고리 이름")
    var categoryNm = categoryNm

    @Column(name = "depth", nullable = false)
    @Comment("카테고리 깊이")
    var depth = depth

    @Column(name = "order_no", nullable = false)
    @Comment("카테고리 이름")
    var orderNo = orderNo

    @Column(name = "delete_flag", nullable = false)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Comment("부모 카테고리 id")
    var parentCategoryEntity = parentCategoryEntity

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @BatchSize(size = 100)
    val chileCategoryList: List<CategoryEntity> = ArrayList<CategoryEntity>()

    fun of(id: Long): CategoryEntity {
        return CategoryEntity(id)
    }

    fun modify(categoryModifyDto: CategoryModifyDto){
        if(categoryModifyDto.parentId != null){
            this.parentCategoryEntity = of(categoryModifyDto.parentId)
        }
        this.categoryNm = categoryModifyDto.categoryNm
        this.depth = categoryModifyDto.depth
        this.orderNo = categoryModifyDto.orderNo
    }

}