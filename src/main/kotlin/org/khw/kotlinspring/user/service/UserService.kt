package org.khw.kotlinspring.user.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.CommonEnum
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.user.domain.dto.UserApiDto
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.dto.UserUpdateDto
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.khw.kotlinspring.user.mapper.UserMapper
import org.khw.kotlinspring.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(val userRepository: UserRepository,
val userMapper: UserMapper) {

    @Transactional(readOnly = true)
    fun findUser(accountId: String) : UserApiDto{
        var findUserEntity = userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N).orElseThrow {IllegalStateException("존재하지 않는 계정 정보입니다.")}
        return userMapper.entityToApiDto(findUserEntity)
    }

    @Transactional
    fun userSave(userSaveDto: UserSaveDto) : UserApiDto{
        if(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N).isPresent){
            throw IllegalStateException("중복된 계정입니다.")
        }
        return userMapper.entityToApiDto(userRepository.save(userMapper.saveDtoToEntity(userSaveDto)))
    }

    @Transactional
    fun userUpdate(userId : Long, userUpdateDto: UserUpdateDto) : UserApiDto{
        var findUserEntity = userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N).orElseThrow{IllegalStateException("존재하지 않는 계정 정보입니다.")};
        findUserEntity.updateUser(userUpdateDto)
        return userMapper.entityToApiDto(userRepository.save(findUserEntity))
    }

    @Transactional
    fun userDelete(userId : Long) : UserApiDto{
        var findUserEntity = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow{IllegalStateException("존재하지 않는 계정 정보입니다.")};
        findUserEntity.deleteUser()
        return userMapper.entityToApiDto(findUserEntity)
    }
}