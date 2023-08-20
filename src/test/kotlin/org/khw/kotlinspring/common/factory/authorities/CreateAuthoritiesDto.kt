package org.khw.kotlinspring.common.factory.authorities

import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesSaveDto

class CreateAuthoritiesDto {

    companion object{
        fun authoritiesSaveDto(): AuthoritiesSaveDto{
            return AuthoritiesSaveDto(1, 1)
        }
    }
}