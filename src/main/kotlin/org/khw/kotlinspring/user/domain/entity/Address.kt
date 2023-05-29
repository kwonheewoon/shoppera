package org.khw.kotlinspring.user.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
data class Address (

    @Column(name = "address", nullable = false)
    @Comment("기본 주소")
    val address : String,

    @Column(name = "address_detail", nullable = false)
    @Comment("상세 주소")
    val addressDetail : String,

    @Column(name = "zip_code", nullable = false)
    @Comment("우편번호")
    val zipCode : String
        ){
}