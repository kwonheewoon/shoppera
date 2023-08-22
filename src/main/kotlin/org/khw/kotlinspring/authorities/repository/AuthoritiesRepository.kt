package org.khw.kotlinspring.authorities.repository

import org.khw.kotlinspring.authorities.domain.entity.AuthoritiesEntity
import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AuthoritiesRepository : JpaRepository <AuthoritiesEntity, Long>{

    fun findByIdAndUserAndDeleteFlag(authoritiesId: Long, findUserEntity: UserEntity, deleteFlag: FlagYn): Optional<AuthoritiesEntity>

}