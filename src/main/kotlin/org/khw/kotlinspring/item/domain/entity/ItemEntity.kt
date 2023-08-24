package org.khw.kotlinspring.item.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.dto.ItemUpdateDto

@Entity
@Table(name = "item")
@Builder
@DynamicInsert
@DynamicUpdate
class ItemEntity(
    id: Long?,
    itemName: String,
    category: CategoryEntity,
    displayFlag: FlagYn = FlagYn.N,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("아이템 아이디")
    val id: Long? = id

    @Column(name = "item_name", nullable = false)
    @Comment("아이템 이름")
    var itemName = itemName
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Comment("카테고리 아이디")
    var category: CategoryEntity = category
        private set

    @Column(name = "display_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("표출여부")
    var displayFlag = displayFlag
        private set


    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    fun update(itemUpdateDto: ItemUpdateDto, findCategoryEntity: CategoryEntity){
        this.itemName = itemUpdateDto.itemName
        this.category = findCategoryEntity
        this.displayFlag = itemUpdateDto.displayFlag
        this.deleteFlag = itemUpdateDto.deleteFlag
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }
}