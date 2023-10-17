package org.khw.shoppera.item.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Where
import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.itemoption.domain.entity.ItemOption

@Entity
@Table(name = "item")
@Builder
@DynamicInsert
@DynamicUpdate
class Item(
    id: Long?,
    itemType: ItemType,
    itemName: String,
    price: Int,
    brand: Brand,
    category: Category,
    itemOptionList: List<ItemOption>?,
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
    var itemType: ItemType = itemType
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
    @JoinColumn(name = "brand_id")
    @Comment("브랜드 아이디")
    var brand: Brand = brand
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Comment("카테고리 아이디")
    var category: Category = category
        private set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "item")
    @Where(clause = "delete_flag = 'N' AND display_flag = 'Y'")
    var itemOptionList = itemOptionList
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

    fun update(itemUpdateDto: ItemUpdateDto, findBrand: Brand, findCategory: Category, findItemType: ItemType){
        this.itemType = findItemType
        this.itemName = itemUpdateDto.itemName
        this.price = itemUpdateDto.price
        this.brand = findBrand
        this.category = findCategory
        this.displayFlag = itemUpdateDto.displayFlag
        this.deleteFlag = itemUpdateDto.deleteFlag
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }

    fun optionAdd(itemOptions: List<ItemOption>){
        this.itemOptionList = itemOptions
    }
}