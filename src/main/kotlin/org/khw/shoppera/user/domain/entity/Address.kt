package org.khw.shoppera.user.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment
import org.khw.shoppera.user.domain.dto.AddressUpdateDto

@Embeddable
data class Address (

    @Column(name = "address", nullable = false)
    @Comment("기본 주소")
    var address : String,

    @Column(name = "address_detail", nullable = false)
    @Comment("상세 주소")
    var addressDetail : String,

    @Column(name = "zip_code", nullable = false)
    @Comment("우편번호")
    var zipCode : String
        ){

    fun updateAddress(addressUpdateDto: AddressUpdateDto){
        this.address = addressUpdateDto.address
        this.addressDetail = addressUpdateDto.addressDetail
        this.zipCode = addressUpdateDto.zipCode
    }
}