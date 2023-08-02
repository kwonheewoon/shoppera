package org.khw.kotlinspring.user.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.common.CommonEnum.FlagYn
import org.khw.kotlinspring.common.modeltemplate.CreateUserDto
import org.khw.kotlinspring.common.modeltemplate.CreateUserEntity
import org.khw.kotlinspring.user.mapper.UserMapper
import org.khw.kotlinspring.user.repository.UserRepository
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var userMapper: UserMapper

    @InjectMocks
    lateinit var userService: UserService

    @Test
    fun `유저 등록 성공`() {
        // Given
        val userSaveDto = CreateUserDto.UserSaveDtoSuccessCreate()
        val userEntity = CreateUserEntity.saveSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreate()

        given(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N)).willReturn(Optional.empty())
        given(userMapper.saveDtoToEntity(userSaveDto)).willReturn(userEntity)
        given(userRepository.save(userEntity)).willReturn(userEntity)
        given(userMapper.entityToApiDto(userEntity)).willReturn(userApiDto)

        // When
        val result = userService.userSave(userSaveDto)

        // Then
        assertEquals(userApiDto, result)
        verify(userMapper).saveDtoToEntity(userSaveDto)
        verify(userRepository).save(userEntity)
        verify(userMapper).entityToApiDto(userEntity)
    }

    @Test
    fun `유저 등록 실패`() {
        // Given
        val userSaveDto = CreateUserDto.UserSaveDtoSuccessCreate()
        val userEntity = CreateUserEntity.saveSuccessCreate()

        // When
        given(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N))
            .willReturn(Optional.of(userEntity))

        // Then
        assertThrows(IllegalStateException::class.java) {
            userService.userSave(userSaveDto)
        }
    }

    @Test
    fun `유저 수정 성공`() {
        // Given
        val userId : Long = 1
        val userUpdateDto = CreateUserDto.UserUpdateDtoSuccessCreate()
        val findUserEntity = CreateUserEntity.saveSuccessCreate()
        val updatedEntity = CreateUserEntity.updateSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreate()

        given(userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N)).willReturn(
            Optional.of(findUserEntity))
        findUserEntity.updateUser(userUpdateDto)
        given(userRepository.save(findUserEntity)).willReturn(findUserEntity)
        given(userMapper.entityToApiDto(findUserEntity)).willReturn(userApiDto)


        // When
        val result = userService.userUpdate(userId, userUpdateDto)

        // Then
        assertEquals(userApiDto, result)
        assertEquals(userApiDto.name, result.name)
        assertEquals(userApiDto.address, result.address)
        assertEquals(userApiDto.phoneNumber, result.phoneNumber)
        verify(userRepository).findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N)
        verify(userRepository).save(findUserEntity)
        verify(userMapper).entityToApiDto(findUserEntity)

    }

    @Test
    fun `유저 수정 실패`() {
        // Given
        val userId : Long = 1
        val userUpdateDto = CreateUserDto.UserUpdateDtoSuccessCreate()
        val findUserEntity = CreateUserEntity.saveSuccessCreate()

        given(userRepository.findByIdAndAccountIdAndDeleteFlag(userId, userUpdateDto.accountId, FlagYn.N)).willReturn(
                Optional.empty())

        // When & Then
        assertThrows(IllegalStateException::class.java) {
            userService.userUpdate(userId, userUpdateDto)
        }

    }

    @Test
    fun `유저 삭제 성공`() {
        // Given
        val userId : Long = 1
        val findUserEntity = CreateUserEntity.saveSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreateOfDelete()

        given(userRepository.findByIdAndDeleteFlag(userId, FlagYn.N)).willReturn(
            Optional.of(findUserEntity))
        findUserEntity.deleteUser()
        given(userMapper.entityToApiDto(findUserEntity)).willReturn(userApiDto)


        // When
        val result = userService.userDelete(userId)

        // Then
        assertEquals(userApiDto, result)
        assertEquals(userApiDto.name, result.name)
        assertEquals(userApiDto.address, result.address)
        assertEquals(userApiDto.phoneNumber, result.phoneNumber)
        verify(userRepository).findByIdAndDeleteFlag(userId, FlagYn.N)
        verify(userMapper).entityToApiDto(findUserEntity)

    }

    @Test
    fun `유저 삭제 실패`() {
        // Given
        val userId : Long = 1

        given(userRepository.findByIdAndDeleteFlag(userId, FlagYn.N)).willReturn(
                Optional.empty())

        // When & Then
        assertThrows(IllegalStateException::class.java) {
            userService.userDelete(userId)
        }

    }

    @Test
    fun `유저 조회 실패`() {
        // Given
        val accountId = "gmldns46"

        given(userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N)).willReturn(
                Optional.empty())

        // When & Then
        assertThrows(IllegalStateException::class.java) {
            userService.findUser(accountId)
        }

    }

    @Test
    fun `유저 조회 성공`() {
        // Given
        val accountId = "gmldns46"
        val findUserEntity = CreateUserEntity.findSuccessCreate()
        val userApiDto = CreateUserDto.UserApiDtoCreateOfDelete()

        given(userRepository.findByAccountIdAndDeleteFlag(accountId, FlagYn.N)).willReturn(
                Optional.of(findUserEntity))
        given(userMapper.entityToApiDto(findUserEntity)).willReturn(userApiDto)

        // When
        val result = userService.findUser(accountId)

        // Then
        assertEquals(userApiDto, result)
        assertEquals(userApiDto.name, result.name)
        assertEquals(userApiDto.address, result.address)
        assertEquals(userApiDto.phoneNumber, result.phoneNumber)
        verify(userRepository).findByAccountIdAndDeleteFlag(accountId, FlagYn.N)
        verify(userMapper).entityToApiDto(findUserEntity)

    }
}