    package org.khw.kotlinspring.user.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.UserException
import org.khw.kotlinspring.user.domain.dto.UserApiDto
import org.khw.kotlinspring.user.domain.dto.UserSaveDto
import org.khw.kotlinspring.user.domain.dto.UserUpdateDto
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
        var findUserEntity = userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        return userMapper.entityToApiDto(findUserEntity)
    }

    @Transactional
    fun userSave(userSaveDto: UserSaveDto) : UserApiDto{
        if(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N).isPresent){
            throw UserException(ResCode.DUPLICATE_USER)
        }
        return userMapper.entityToApiDto(userRepository.save(userMapper.saveDtoToEntity(userSaveDto)))
    }

    @Transactional
    fun userUpdate(userId : Long, userUpdateDto: UserUpdateDto) : UserApiDto{
        var findUserEntity = userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUserEntity.updateUser(userUpdateDto)
        return userMapper.entityToApiDto(userRepository.save(findUserEntity))
    }

    @Transactional
    fun userDelete(userId : Long) : UserApiDto{
        var findUserEntity = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUserEntity.deleteUser()
        return userMapper.entityToApiDto(findUserEntity)
    }
}