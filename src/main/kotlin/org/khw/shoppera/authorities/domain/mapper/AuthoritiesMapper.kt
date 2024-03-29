package org.khw.shoppera.authorities.domain.mapper

import org.khw.shoppera.authorities.domain.dto.AuthoritiesViewApiDto
import org.khw.shoppera.authorities.domain.entity.AuthoritiesEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface AuthoritiesMapper {

    @Mappings(
        Mapping(source = "user.id", target = "userId"),
        Mapping(source = "authority.id", target = "authorityId"),
    )
    fun entityToViewApiDto(authoritiesEntity: AuthoritiesEntity) : AuthoritiesViewApiDto


}