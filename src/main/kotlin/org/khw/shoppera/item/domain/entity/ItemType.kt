package org.khw.shoppera.item.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.item.domain.dto.ItemTypeUpdateDto

@Entity
@Table(name = "item_type")
@Builder
@DynamicInsert
@DynamicUpdate
class ItemType(
    id: Long?,
    typeCode: String,
    typeName: String,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("아이템 아이디")
    val id: Long? = id

    @Column(name = "item_code", nullable = false)
    @Comment("타입 코드")
    var typeCode = typeCode
        private set

    @Column(name = "type_name", nullable = false)
    @Comment("타입 명")
    var typeName = typeName
        private set


    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    fun update(itemTypeUpdateDto: ItemTypeUpdateDto){
        this.typeCode = itemTypeUpdateDto.typeCode
        this.typeName = itemTypeUpdateDto.typeName
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }
}