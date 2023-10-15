package org.khw.shoppera.user.mapper

import org.khw.shoppera.user.domain.dto.UserApiDto
import org.khw.shoppera.user.domain.dto.UserSaveDto
import org.khw.shoppera.user.domain.dto.UserUpdateDto
import org.khw.shoppera.user.domain.entity.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    //@Mapping(target = "deleteFlag", ignore = true)
    fun saveDtoToEntity(userSaveDto: UserSaveDto) : UserEntity

    fun updateDtoToEntity(userUpdateDto: UserUpdateDto) : UserEntity

    fun entityToApiDto(userEntity: UserEntity) : UserApiDto
}