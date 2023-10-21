    package org.khw.shoppera.user.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.user.domain.dto.UserApiDto
import org.khw.shoppera.user.domain.dto.UserSaveDto
import org.khw.shoppera.user.domain.dto.UserUpdateDto
import org.khw.shoppera.user.mapper.UserMapper
import org.khw.shoppera.user.repository.UserRepository
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
        val findUser = userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        return userMapper.entityToApiDto(findUser)
    }

    @Transactional
    fun userSave(userSaveDto: UserSaveDto) : UserApiDto{
        if(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N).isPresent){
            throw UserException(ResCode.DUPLICATE_USER)
        }

        val savedUser = userMapper.saveDtoToEntity(userSaveDto)
        savedUser.passwordEnc(bCryptPasswordEncoder.encode(savedUser.password))

        return userMapper.entityToApiDto(userRepository.save(savedUser))
    }

    @Transactional
    fun userUpdate(userId : Long, userUpdateDto: UserUpdateDto) : UserApiDto{
        val findUser = userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUser.updateUser(userUpdateDto)
        return userMapper.entityToApiDto(userRepository.save(findUser))
    }

    @Transactional
    fun userPasswordUpdate(userId: Long, password: String){
        val findUser = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUser.updatePassword(bCryptPasswordEncoder.encode(password))
    }

    @Transactional
    fun userDelete(userId : Long) : UserApiDto{
        val findUser = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow{ UserException(ResCode.NOT_FOUND_USER) };
        findUser.deleteUser()
        return userMapper.entityToApiDto(findUser)
    }
}