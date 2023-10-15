package org.khw.shoppera.common.factory.authorities

import org.khw.shoppera.authorities.domain.entity.AuthoritiesEntity
import org.khw.shoppera.authorities.domain.entity.AuthorityEntity
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.factory.user.CreateUserEntity
import org.khw.shoppera.user.domain.entity.UserEntity

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