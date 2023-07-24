package org.khw.kotlinspring.user.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.khw.kotlinspring.user.mapper.UserMapper
import org.khw.kotlinspring.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(val userRepository: UserRepository,
val userMapper: UserMapper) {

    @Transactional
    fun userSave(userSaveDto: UserSaveDto) : UserEntity{
        if(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N).isPresent){
            throw IllegalStateException("중복된 계정입니다.")
        }
        return userRepository.save(userMapper.saveDtoToEntity(userSaveDto))
    }
}