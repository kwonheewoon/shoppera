package org.khw.shoppera.coupon.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.common.enums.CommonEnum.*
import org.khw.shoppera.coupon.domain.dto.CouponUpdateApiDto
import java.time.LocalDate

@Entity
@Table(name = "coupon")
@Builder
@DynamicInsert
@DynamicUpdate
class Coupon(
    id: Long?,
    couponName: String,
    discountRate: Int,
    expireDate: LocalDate,
    brand: Brand,
    deleteFlag: FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("쿠폰 아이디")
    val id: Long? = id

    @Column(name = "coupon_name", nullable = false)
    @Comment("쿠폰 이름")
    var couponName = couponName
        private set

    @Column(name = "discount_rate", nullable = false)
    @Comment("할인율")
    var discountRate = discountRate
        private set

    @Column(name = "expire_date", nullable = false)
    @Comment("만료 일자")
    var expireDate = expireDate
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @Comment("브랜드 아이디")
    var brand: Brand = brand
        private set

    @Column(name = "delete_flag", nullable = false)
    @Comment("삭제 여부")
    var deleteFlag = deleteFlag
        private set


    fun update(couponUpdateApiDto: CouponUpdateApiDto, brand: Brand){
        this.couponName = couponUpdateApiDto.couponName
        this.discountRate = couponUpdateApiDto.discountRate
        this.expireDate = couponUpdateApiDto.expireDate
        this.brand = brand
    }

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }

}