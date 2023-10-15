package org.khw.shoppera.coupon.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.khw.shoppera.common.enums.CommonEnum.*
import java.time.LocalDate

data class CouponViewApiDto(
    @Schema(description = "쿠폰 아이디", example = "1")
    val id: Long,

    @Schema(description = "쿠폰 이름", example = "20% 쿠폰")
    val couponName: String,

    @Schema(description = "할인율", example = "20")
    val discountRate: Int,

    @Schema(description = "만료 일자", example = "2023-09-23")
    @JsonFormat(pattern = "yyyy-MM-dd")
    val expireDate: LocalDate,

    @Schema(description = "삭제 여부", example = "N")
    val deleteFlag: FlagYn,
)