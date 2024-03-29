package org.khw.shoppera.category.domain.entity

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.category.domain.dto.CategoryModifyDto
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import java.util.*

@Entity
@Table(name = "category")
@Builder
@DynamicInsert
@DynamicUpdate
class Category(id: Long?,
               categoryNm: String,
               orderNo: Int,
               depth: Int = 1,
               deleteFlag: FlagYn = FlagYn.N,
               parentCategory: Category? = null,
               childCategoryList: List<Category> = ArrayList<Category>()
) {

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("카테고리 아이디")
    val id : Long? = id

    @Column(name = "category_nm", nullable = false)
    @Comment("카테고리 이름")
    var categoryNm = categoryNm
        private set

    @Column(name = "depth", nullable = false)
    @Comment("카테고리 깊이")
    var depth = depth
        private set

    @Column(name = "order_no", nullable = false)
    @Comment("정렬 순서")
    var orderNo = orderNo
        private set

    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Comment("부모 카테고리 id")
    var parentCategory = parentCategory
        private set

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @BatchSize(size = 100)
    var childCategoryList: List<Category> = childCategoryList
        private set



    
    fun modify(categoryModifyDto: CategoryModifyDto){
        this.categoryNm = categoryModifyDto.categoryNm
        this.depth = categoryModifyDto.depth
        this.orderNo = categoryModifyDto.orderNo
    }

}