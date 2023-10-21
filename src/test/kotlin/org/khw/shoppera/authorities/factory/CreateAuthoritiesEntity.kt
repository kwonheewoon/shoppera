package org.khw.shoppera.common.factory.authorities

import org.khw.shoppera.authorities.domain.entity.AuthoritiesEntity
import org.khw.shoppera.authorities.domain.entity.AuthorityEntity
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.user.domain.entity.User

class CreateAuthoritiesEntity {

    companion object{

        fun findAuthorityEntity(): AuthorityEntity{
            return AuthorityEntity(1L, "write")
        }

        fun findAuthoritiesEntity(findUser: User, findAuthorityEntity: AuthorityEntity): AuthoritiesEntity{
            return AuthoritiesEntity(1L, findUser, findAuthorityEntity, CommonEnum.FlagYn.N)
        }

        fun authoritiesEntity(findUser: User, findAuthorityEntity: AuthorityEntity): AuthoritiesEntity{
            return AuthoritiesEntity(null, findUser, findAuthorityEntity, CommonEnum.FlagYn.N)
        }
    }
}