package org.khw.shoppera.authorities.repository

import org.khw.shoppera.authorities.domain.entity.AuthoritiesEntity
import org.khw.shoppera.authorities.domain.entity.AuthorityEntity
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AuthoritiesRepository : JpaRepository <AuthoritiesEntity, Long>{

    fun findByIdAndUserAndDeleteFlag(authoritiesId: Long, findUserEntity: UserEntity, deleteFlag: FlagYn): Optional<AuthoritiesEntity>

    fun countByUserAndAuthorityAndDeleteFlag(findUserEntity: UserEntity, findAuthorityEntity: AuthorityEntity, deleteFlag: FlagYn): Int

}