package org.khw.kotlinspring.authorities.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.khw.kotlinspring.authorities.repository.AuthoritiesRepository
import org.khw.kotlinspring.authorities.repository.AuthorityRepository
import org.khw.kotlinspring.common.factory.authorities.CreateAuthoritiesEntity
import org.khw.kotlinspring.common.factory.user.CreateUserEntity
import org.khw.kotlinspring.user.repository.UserRepository
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.khw.kotlinspring.authorities.domain.entity.AuthoritiesEntity
import org.khw.kotlinspring.authorities.domain.mapper.AuthoritiesMapper
import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.AuthoritiesException
import org.khw.kotlinspring.common.exception.UserException
import org.khw.kotlinspring.common.factory.authorities.CreateAuthoritiesDto
import org.mockito.BDDMockito
import org.mockito.BDDMockito.any
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockitoExtension::class)
class AuthoritiesServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var authorityRepository: AuthorityRepository

    @Mock
    lateinit var authoritiesRepository: AuthoritiesRepository

    @Mock
    lateinit var authoritiesMapper: AuthoritiesMapper

    @InjectMocks
    lateinit var authoritiesService: AuthoritiesService

    @Test
    fun `사용자 권한 등록 성공`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val authoritiesViewApiDto = CreateAuthoritiesDto.authoritiesViewApiDto()
        val findUserEntity = CreateUserEntity.findSuccessCreate()
        val findAuthorityEntity = CreateAuthoritiesEntity.findAuthorityEntity()
        val savedAuthoritiesEntity = CreateAuthoritiesEntity.findAuthoritiesEntity(findUserEntity, findAuthorityEntity)

        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUserEntity))
        given(authorityRepository.findById(authoritiesSaveDto.authorityId))
            .willReturn(Optional.of(findAuthorityEntity))
        given(authoritiesRepository.countByUserAndAuthorityAndDeleteFlag(findUserEntity, findAuthorityEntity, FlagYn.N))
            .willReturn(0)
        given(authoritiesRepository.save(any(AuthoritiesEntity::class.java)))
            .willReturn(savedAuthoritiesEntity)
        given(authoritiesMapper.entityToViewApiDto(savedAuthoritiesEntity))
            .willReturn(authoritiesViewApiDto)

        // When
        val result = authoritiesService.saveAuthorities(authoritiesSaveDto)

        // Then
        verify(authoritiesRepository).save(any(AuthoritiesEntity::class.java))
        assertEquals(authoritiesViewApiDto, result)
    }

    @Test
    fun `사용자 권한 등록 실패(존재 하지 않는 유저)`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()

        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(UserException::class.java){
            authoritiesService.saveAuthorities(authoritiesSaveDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_USER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_USER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_USER.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `사용자 권한 등록 실패(존재 하지 않는 권한)`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val findUserEntity = CreateUserEntity.findSuccessCreate()


        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUserEntity))
        given(authorityRepository.findById(authoritiesSaveDto.authorityId))
            .willReturn(Optional.empty())


        // When
        val throwable = assertThrows(AuthoritiesException::class.java){
            authoritiesService.saveAuthorities(authoritiesSaveDto)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_AUTHORITY.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_AUTHORITY.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_AUTHORITY.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `사용자 권한 등록 실패(중복되는 권한 정보)`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val findUserEntity = CreateUserEntity.findSuccessCreate()
        val findAuthorityEntity = CreateAuthoritiesEntity.findAuthorityEntity()



        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUserEntity))
        given(authorityRepository.findById(authoritiesSaveDto.authorityId))
            .willReturn(Optional.of(findAuthorityEntity))
        given(authoritiesRepository.countByUserAndAuthorityAndDeleteFlag(findUserEntity, findAuthorityEntity, FlagYn.N))
            .willReturn(1)


        // When
        val throwable = assertThrows(AuthoritiesException::class.java){
            authoritiesService.saveAuthorities(authoritiesSaveDto)
        }

        // Then
        assertEquals(ResCode.DUPLICATE_AUTHORITIES.code, throwable.code)
        assertEquals(ResCode.DUPLICATE_AUTHORITIES.message, throwable.message)
        assertEquals(ResCode.DUPLICATE_AUTHORITIES.httpStatus, HttpStatus.CONFLICT)
    }

    @Test
    fun `사용자 권한 삭제 실패(존재 하지 않는 유저)`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val authoritiesId: Long = 2
        val userId: Long = 1


        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(UserException::class.java){
            authoritiesService.deleteAuthorities(authoritiesId, userId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_USER.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_USER.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_USER.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `사용자 권한 삭제 실패(존재 하지 않는 권한 정보)`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val findUserEntity = CreateUserEntity.findSuccessCreate()
        val authoritiesId: Long = 2
        val userId: Long = 1


        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUserEntity))
        given(authoritiesRepository.findByIdAndUserAndDeleteFlag(authoritiesId, findUserEntity, FlagYn.N))
            .willReturn(Optional.empty())

        // When
        val throwable = assertThrows(AuthoritiesException::class.java){
            authoritiesService.deleteAuthorities(authoritiesId, userId)
        }

        // Then
        assertEquals(ResCode.NOT_FOUND_AUTHORITIES.code, throwable.code)
        assertEquals(ResCode.NOT_FOUND_AUTHORITIES.message, throwable.message)
        assertEquals(ResCode.NOT_FOUND_AUTHORITIES.httpStatus, HttpStatus.NOT_FOUND)
    }

    @Test
    fun `사용자 권한 삭제 성공`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val findUserEntity = CreateUserEntity.findSuccessCreate()
        val findAuthoritiesEntity = CreateAuthoritiesEntity.findAuthoritiesEntity(findUserEntity, CreateAuthoritiesEntity.findAuthorityEntity())
        val authoritiesId: Long = 2
        val userId: Long = 1


        given(userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N))
            .willReturn(Optional.of(findUserEntity))
        given(authoritiesRepository.findByIdAndUserAndDeleteFlag(authoritiesId, findUserEntity, FlagYn.N))
            .willReturn(Optional.of(findAuthoritiesEntity))
        findAuthoritiesEntity.delete()

        // When
        authoritiesService.deleteAuthorities(authoritiesId, userId)


        // Then
        verify(userRepository).findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N)
        verify(authoritiesRepository).findByIdAndUserAndDeleteFlag(authoritiesId, findUserEntity, FlagYn.N)
        assertEquals(findAuthoritiesEntity.deleteFlag, FlagYn.Y)
    }
}