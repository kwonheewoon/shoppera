package org.khw.shoppera.authorities.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "authority")
@Builder
@DynamicInsert
@DynamicUpdate
class AuthorityEntity(
        id: Long,
        authority : String
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("기본키")
    val id = id

    @Column(name = "authority", nullable = false)
    @Comment("권한명")
    var authority = authority
}