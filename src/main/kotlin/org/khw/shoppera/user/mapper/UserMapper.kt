package org.khw.shoppera.user.mapper

import org.khw.shoppera.user.domain.dto.UserApiDto
import org.khw.shoppera.user.domain.dto.UserSaveDto
import org.khw.shoppera.user.domain.dto.UserUpdateDto
import org.khw.shoppera.user.domain.entity.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {

    //@Mapping(target = "deleteFlag", ignore = true)
    fun saveDtoToEntity(userSaveDto: UserSaveDto) : User

    fun updateDtoToEntity(userUpdateDto: UserUpdateDto) : User

    fun entityToApiDto(user: User) : UserApiDto
}