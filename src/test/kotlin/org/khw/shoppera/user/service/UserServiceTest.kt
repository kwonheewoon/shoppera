package org.khw.shoppera.user.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.common.factory.user.CreateUserDto
import org.khw.shoppera.common.factory.user.CreateUserEntity
import org.khw.shoppera.user.mapper.UserMapper
import org.khw.shoppera.user.repository.UserRepository
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var userMapper: UserMapper

    @Mock
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @InjectMocks
    lateinit var userService: UserService

    @Test
    fun `유저 등록 성공`() {
        // Given
        val userSaveDto = CreateUserDto.UserSaveDtoSuccessCreate()
        val saveUser = CreateUserEntity.saveUserEntity()
        val savedUser = CreateUserEntity.savedSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreate()

        given(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N)).willReturn(Optional.empty())
        given(userMapper.saveDtoToEntity(userSaveDto)).willReturn(saveUser)
        given(bCryptPasswordEncoder.encode(userSaveDto.password))
            .willReturn("\$2a\$10\$9xnv/5N67pIo2ppDLEyWwumb2kQe3TX4tvSt.t8mQKlRsUo6eQVci")
        given(userRepository.save(saveUser)).willReturn(savedUser)
        given(userMapper.entityToApiDto(savedUser)).willReturn(userApiDto)

        // When
        val result = userService.userSave(userSaveDto)

        // Then
        assertEquals(userApiDto, result)
        verify(userRepository).findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N)
        verify(userMapper).saveDtoToEntity(userSaveDto)
        verify(bCryptPasswordEncoder).encode(userSaveDto.password)
        verify(userRepository).save(saveUser)
        verify(userMapper).entityToApiDto(savedUser)
    }

    @Test
    fun `유저 등록 실패`() {
        // Given
        val userSaveDto = CreateUserDto.UserSaveDtoSuccessCreate()
        val saveUser = CreateUserEntity.savedSuccessCreate()

        // When
        given(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N))
            .willReturn(Optional.of(saveUser))

        // Then
        val throwable = assertThrows(UserException::class.java) {
            userService.userSave(userSaveDto)
        }

        assertEquals(throwable.code, ResCode.DUPLICATE_USER.code)
        assertEquals(throwable.message, ResCode.DUPLICATE_USER.message)
        assertEquals(throwable.httpStatus, ResCode.DUPLICATE_USER.httpStatus)
    }

    @Test
    fun `유저 수정 성공`() {
        // Given
        val userId : Long = 1
        val userUpdateDto = CreateUserDto.UserUpdateDtoSuccessCreate()
        val findUser = CreateUserEntity.savedSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreate()

        given(userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N)).willReturn(
            Optional.of(findUser))
        findUser.updateUser(userUpdateDto)
        given(userRepository.save(findUser)).willReturn(findUser)
        given(userMapper.entityToApiDto(findUser)).willReturn(userApiDto)


        // When
        val result = userService.userUpdate(userId, userUpdateDto)

        // Then
        assertEquals(userApiDto, result)
        assertEquals(userApiDto.name, result.name)
        assertEquals(userApiDto.address, result.address)
        assertEquals(userApiDto.phoneNumber, result.phoneNumber)
        verify(userRepository).findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N)
        verify(userRepository).save(findUser)
        verify(userMapper).entityToApiDto(findUser)

    }

    @Test
    fun `유저 수정 실패`() {
        // Given
        val userId : Long = 1
        val userUpdateDto = CreateUserDto.UserUpdateDtoSuccessCreate()

        given(userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N)).willReturn(
                Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java) {
            userService.userUpdate(userId, userUpdateDto)
        }

        // Then
        assertEquals(throwable.code, ResCode.NOT_FOUND_USER.code)
        assertEquals(throwable.message, ResCode.NOT_FOUND_USER.message)
        assertEquals(throwable.httpStatus, ResCode.NOT_FOUND_USER.httpStatus)

    }

    @Test
    fun `유저 삭제 성공`() {
        // Given
        val userId : Long = 1
        val findUser = CreateUserEntity.findSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreateOfDelete()

        given(userRepository.findByIdAndDeleteFlag(userId, FlagYn.N)).willReturn(
            Optional.of(findUser))
        findUser.deleteUser()
        given(userMapper.entityToApiDto(findUser)).willReturn(userApiDto)


        // When
        val result = userService.userDelete(userId)

        // Then
        assertEquals(userApiDto, result)
        assertEquals(userApiDto.name, result.name)
        assertEquals(userApiDto.address, result.address)
        assertEquals(userApiDto.phoneNumber, result.phoneNumber)
        verify(userRepository).findByIdAndDeleteFlag(userId, FlagYn.N)
        verify(userMapper).entityToApiDto(findUser)

    }

    @Test
    fun `유저 삭제 실패`() {
        // Given
        val userId : Long = 1

        given(userRepository.findByIdAndDeleteFlag(userId, FlagYn.N)).willReturn(
                Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java) {
            userService.userDelete(userId)
        }

        // Then
        assertEquals(throwable.code, ResCode.NOT_FOUND_USER.code)
        assertEquals(throwable.message, ResCode.NOT_FOUND_USER.message)
        assertEquals(throwable.httpStatus, ResCode.NOT_FOUND_USER.httpStatus)
    }

    @Test
    fun `유저 조회 실패`() {
        // Given
        val accountId = "gmldns46"

        given(userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N)).willReturn(
                Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java) {
            userService.findUser(accountId)
        }

        // Then
        assertEquals(throwable.code, ResCode.NOT_FOUND_USER.code)
        assertEquals(throwable.message, ResCode.NOT_FOUND_USER.message)
        assertEquals(throwable.httpStatus, ResCode.NOT_FOUND_USER.httpStatus)

    }

    @Test
    fun `유저 조회 성공`() {
        // Given
        val accountId = "gmldns46"
        val findUser = CreateUserEntity.findSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreateOfDelete()

        given(userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N)).willReturn(
                Optional.of(findUser))
        given(userMapper.entityToApiDto(findUser)).willReturn(userApiDto)

        // When
        val result = userService.findUser(accountId)

        // Then
        assertEquals(userApiDto, result)
        assertEquals(userApiDto.name, result.name)
        assertEquals(userApiDto.address, result.address)
        assertEquals(userApiDto.phoneNumber, result.phoneNumber)
        verify(userRepository).findByAccountIdAndDeleteFlag(accountId, FlagYn.N)
        verify(userMapper).entityToApiDto(findUser)

    }

    @Test
    fun `유저 비밀번호 변경 성공`() {
        // Given
        val userId : Long = 1
        val changePassword = "4444"
        val findUser = CreateUserEntity.findSuccessCreate()

        given(userRepository.findByIdAndDeleteFlag(userId, FlagYn.N)).willReturn(
            Optional.of(findUser))
        given(bCryptPasswordEncoder.encode(changePassword))
            .willReturn("\$2a\$10\$9xnv/5N67pIo2ppDLEyWwumb2kQe3TX4tvSt.t8mQKlRsUo6eQVci")


        // When
        userService.userPasswordUpdate(userId, changePassword)

        // Then
        verify(userRepository).findByIdAndDeleteFlag(userId, FlagYn.N)

    }

    @Test
    fun `유저 비밀번호 변경 실패(존재 하지 않는 유저)`() {
        // Given
        val userId : Long = 1
        val changePassword = "4444"

        given(userRepository.findByIdAndDeleteFlag(userId, FlagYn.N)).willReturn(
            Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java) {
            userService.userPasswordUpdate(userId, changePassword)
        }

        // Then
        assertEquals(throwable.code, ResCode.NOT_FOUND_USER.code)
        assertEquals(throwable.message, ResCode.NOT_FOUND_USER.message)
        assertEquals(throwable.httpStatus, ResCode.NOT_FOUND_USER.httpStatus)

    }
}