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
        val userEntity = CreateUserEntity.UserEntitySuccessCreate()

        given(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N)).willReturn(Optional.empty())
        given(userMapper.saveDtoToEntity(userSaveDto)).willReturn(userEntity)
        given(userRepository.save(userEntity)).willReturn(userEntity)

        // When
        val result = userService.userSave(userSaveDto)

        // Then
        assertEquals(userEntity, result)
        verify(userMapper).saveDtoToEntity(userSaveDto)
        verify(userRepository).save(userEntity)
    }

    @Test
    fun `유저 등록 실패`() {
        // Given
        val userSaveDto = CreateUserDto.UserSaveDtoSuccessCreate()
        val userEntity = CreateUserEntity.UserEntitySuccessCreate()

        // When
        given(userRepository.findByAccountIdAndDeleteFlag(userSaveDto.accountId, FlagYn.N))
            .willReturn(Optional.of(userEntity))

        // Then
        assertThrows(IllegalStateException::class.java) {
            userService.userSave(userSaveDto)
        }
    }
}