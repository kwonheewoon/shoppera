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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(val userRepository: UserRepository,
val userMapper: UserMapper,
    val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @Transactional(readOnly = true)
    fun findUser(accountId: String) : UserApiDto{
        val findUserEntity = userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        return userMapper.entityToApiDto(findUserEntity)
    }

    @Transactional
    fun userSave(userSaveDto: UserSaveDto) : UserApiDto{
        if(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N).isPresent){
            throw UserException(ResCode.DUPLICATE_USER)
        }

        val userEntity = userMapper.saveDtoToEntity(userSaveDto)
        userEntity.passwordEnc(bCryptPasswordEncoder.encode(userEntity.password))

        return userMapper.entityToApiDto(userRepository.save(userEntity))
    }

    @Transactional
    fun userUpdate(userId : Long, userUpdateDto: UserUpdateDto) : UserApiDto{
        val findUserEntity = userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUserEntity.updateUser(userUpdateDto)
        return userMapper.entityToApiDto(userRepository.save(findUserEntity))
    }

    @Transactional
    fun userPasswordUpdate(userId: Long, password: String){
        val findUserEntity = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUserEntity.updatePassword(bCryptPasswordEncoder.encode(password))
    }

    @Transactional
    fun userDelete(userId : Long) : UserApiDto{
        val findUserEntity = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUserEntity.deleteUser()
        return userMapper.entityToApiDto(findUserEntity)
    }
}