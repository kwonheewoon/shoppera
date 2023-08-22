package org.khw.kotlinspring.authorities.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.user.domain.entity.UserEntity

@Entity
@Table(name = "authorities")
@Builder
@DynamicInsert
@DynamicUpdate
class AuthoritiesEntity(
        id: Long?,
        user : UserEntity,
        authority : AuthorityEntity,
        deleteFlag : FlagYn = FlagYn.N
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("기본키")
    val id = id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("권한자 ID")
    val user = user

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id", nullable = false)
    @Comment("권한")
    val authority = authority

    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    fun delete(){
        this.deleteFlag = FlagYn.Y
    }
}