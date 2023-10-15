package org.khw.shoppera.common.factory.authorities

import org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.shoppera.authorities.domain.dto.AuthoritiesViewApiDto

class CreateAuthoritiesDto {

    companion object{
        fun authoritiesSaveDto(): org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto {
            return org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto(1, 1)
        }

        fun authoritiesViewApiDto(): AuthoritiesViewApiDto{
            return AuthoritiesViewApiDto(1, 1, 1)
        }
    }
}