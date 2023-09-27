package org.khw.kotlinspring.coupon.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.coupon.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CouponService(val couponRepository: CouponRepository) {
}