package org.khw.kotlinspring.user.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.common.enums.CommonEnum.*
import org.khw.kotlinspring.user.domain.dto.UserUpdateDto
import java.time.LocalDate

@Entity
@Table(name = "users")
@Builder
@DynamicInsert
@DynamicUpdate
class UserEntity(id: Long,
    name : String,
    accountId : String,
    birthDate : LocalDate,
    address : Address,
    phoneNumber : String,
    deleteFlag : FlagYn = FlagYn.N
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("기본키")
    val id : Long = id

    @Column(name = "account_id", nullable = false)
    @Comment("아이디")
    val accountId = accountId

    @Column(name = "name", nullable = false)
    @Comment("이름")
    var name = name
        private set

    @Column(name = "birth_date", nullable = false)
    @Comment("생년월일")
    val birthDate = birthDate

    @Embedded
    var address = address
        private set

    @Column(name = "phone_number", nullable = false)
    @Comment("휴대폰 번호")
    var phoneNumber = phoneNumber
        private set

    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    fun updateUser(userUpdateDto : UserUpdateDto){
        this.name = userUpdateDto.name
        this.address.updateAddress(userUpdateDto.address)
        this.phoneNumber = userUpdateDto.phoneNumber
    }

    fun deleteUser(){
        this.deleteFlag = FlagYn.Y
    }
}