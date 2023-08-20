package org.khw.kotlinspring.common.factory.authorities

import org.khw.kotlinspring.authorities.domain.entity.AuthoritiesEntity
import org.khw.kotlinspring.authorities.domain.entity.AuthorityEntity
import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.factory.user.CreateUserEntity
import org.khw.kotlinspring.user.domain.entity.UserEntity

class CreateAuthoritiesEntity {

    companion object{

        fun findAuthorityEntity(): AuthorityEntity{
            return AuthorityEntity(1L, "write")
        }

        fun findAuthoritiesEntity(findUserEntity: UserEntity, findAuthorityEntity: AuthorityEntity): AuthoritiesEntity{
            return AuthoritiesEntity(1L, findUserEntity, findAuthorityEntity, CommonEnum.FlagYn.N)
        }

        fun authoritiesEntity(findUserEntity: UserEntity, findAuthorityEntity: AuthorityEntity): AuthoritiesEntity{
            return AuthoritiesEntity(null, findUserEntity, findAuthorityEntity, CommonEnum.FlagYn.N)
        }
    }
}