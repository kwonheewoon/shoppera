package org.khw.kotlinspring.common.factory.authorities

import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesViewApiDto

class CreateAuthoritiesDto {

    companion object{
        fun authoritiesSaveDto(): AuthoritiesSaveDto{
            return AuthoritiesSaveDto(1, 1)
        }

        fun authoritiesViewApiDto(): AuthoritiesViewApiDto{
            return AuthoritiesViewApiDto(1, 1, 1)
        }
    }
}