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
    itemType: ItemTypeEntity,
    itemName: String,
    price: Int,
    category: CategoryEntity,
    displayFlag: FlagYn = FlagYn.N,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("아이템 아이디")
    val id: Long? = id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    @Comment("아이템 타입 아이디")
    var itemType: ItemTypeEntity = itemType
        private set

    @Column(name = "item_name", nullable = false)
    @Comment("아이템 이름")
    var itemName = itemName
        private set

    @Column(name = "price", nullable = false)
    @Comment("가격")
    var price = price
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

    fun update(itemUpdateDto: ItemUpdateDto, findCategoryEntity: CategoryEntity, findItemTypeEntity: ItemTypeEntity){
        this.itemType = findItemTypeEntity
        this.itemName = itemUpdateDto.itemName
        this.price = itemUpdateDto.price
        this.category = findCategoryEntity
        this.displayFlag = itemUpdateDto.displayFlag
        this.deleteFlag = itemUpdateDto.deleteFlag
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }
}