package org.khw.kotlinspring.user.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.khw.kotlinspring.user.mapper.UserMapper
import org.khw.kotlinspring.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(val userRepository: UserRepository,
val userMapper: UserMapper) {

    fun userSave(userSaveDto: UserSaveDto) : UserEntity{
        return userRepository.save(userMapper.saveDtoToEntity(userSaveDto))
    }
}