package org.khw.shoppera.common.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Embeddable
@EntityListeners(AuditingEntityListener::class)
class BaseEntity (

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("등록 일자")
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(nullable = false)
    @Comment("수정 일자")
    var updatedAt: LocalDateTime? = null,

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @Comment("등록자")
    var createdBy: String? = null,

    @LastModifiedBy
    @Column(nullable = false)
    @Comment("수정자")
    var updatedBy: String? = null
)