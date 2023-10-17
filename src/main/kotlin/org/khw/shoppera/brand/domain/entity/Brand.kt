package org.khw.shoppera.brand.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.brand.domain.dto.BrandUpdateApiDto
import org.khw.shoppera.common.enums.CommonEnum.FlagYn

@Entity
@Table(name = "brand")
@Builder
@DynamicInsert
@DynamicUpdate
class Brand(
    id: Long?,
    name: String,
    explanation: String,
    foundedYear: Int,
    displayFlag: FlagYn = FlagYn.N,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("아이템 아이디")
    val id: Long? = id

    @Column(name = "name", nullable = false)
    @Comment("브랜드 이름")
    var name = name
        private set

    @Column(name = "explanation", nullable = false)
    @Comment("브랜드 설명")
    var explanation = explanation
        private set

    @Column(name = "founded_year", nullable = false)
    @Comment("브랜드 설립 년도")
    var foundedYear = foundedYear
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


    fun update(brandUpdateApiDto: BrandUpdateApiDto){
        this.name = brandUpdateApiDto.name
        this.explanation = brandUpdateApiDto.explanation
        this.foundedYear = brandUpdateApiDto.foundedYear
        this.displayFlag = brandUpdateApiDto.displayFlag
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }

}