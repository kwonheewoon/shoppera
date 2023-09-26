package org.khw.kotlinspring.itemoption.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.item.domain.entity.ItemEntity


@Entity
@Table(name = "item_options")
@Builder
@DynamicInsert
@DynamicUpdate
class ItemOption(
    id: Long?,
    item: ItemEntity?,
    optionName: String,
    orderNo: Int,
    displayFlag: FlagYn = FlagYn.N,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("옵션 아이디")
    val id: Long? = id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @Comment("아이템 아이디")
    var item = item
        private set

    @Column(name = "option_name", nullable = false)
    @Comment("옵션 이름")
    var optionName = optionName
        private set

    @Column(name = "order_no", nullable = false)
    @Comment("옵션 순번")
    var orderNo = orderNo
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

    fun delete(){
        this.displayFlag = FlagYn.N
        this.deleteFlag = FlagYn.Y
    }
}