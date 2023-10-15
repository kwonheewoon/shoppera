package org.khw.shoppera.authorities.domain.entity

import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.user.domain.entity.UserEntity

class AuthoritiesEntityFactory {

    companion object{
        /**
         * AuthoritiesEntity Entity 생성 팩토리 메소드
         *
         * @param findUserEntity 유저 Entity
         * @param findAuthorityEntity 권한 Entity
         * @return AuthoritiesEntity
         */
        fun createAuthoritiesEntity(findUserEntity: UserEntity, findAuthorityEntity: AuthorityEntity): AuthoritiesEntity{
            return AuthoritiesEntity(null, findUserEntity, findAuthorityEntity, FlagYn.N)
        }
    }
}