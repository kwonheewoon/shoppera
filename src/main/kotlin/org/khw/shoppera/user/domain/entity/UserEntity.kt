package org.khw.shoppera.user.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.shoppera.authorities.domain.entity.AuthoritiesEntity
import org.khw.shoppera.common.enums.CommonEnum.*
import org.khw.shoppera.user.domain.dto.UserUpdateDto
import java.time.LocalDate

@Entity
@Table(name = "users")
@Builder
@DynamicInsert
@DynamicUpdate
class UserEntity(id: Long?,
    name : String,
    accountId : String,
    password : String,
    birthDate : LocalDate,
    address : Address,
    phoneNumber : String,
    authorities: List<AuthoritiesEntity>?,
    deleteFlag : FlagYn = FlagYn.N
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("기본키")
    val id : Long? = id

    @Column(name = "account_id", nullable = false)
    @Comment("아이디")
    val accountId = accountId

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    @Comment("비밀번호")
    var password = password
        private set

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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var authorities = authorities
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

    fun updatePassword(changePassword: String){
        this.password = changePassword
    }

    fun passwordEnc(encPassword: String){
        this.password = encPassword
    }

    fun deleteUser(){
        this.deleteFlag = FlagYn.Y
    }
}