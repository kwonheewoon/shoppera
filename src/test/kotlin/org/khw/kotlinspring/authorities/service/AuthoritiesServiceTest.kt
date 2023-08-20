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
import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.factory.authorities.CreateAuthoritiesDto
import org.mockito.BDDMockito
import org.mockito.BDDMockito.any
import java.util.*

@ExtendWith(MockitoExtension::class)
class AuthoritiesServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var authorityRepository: AuthorityRepository

    @Mock
    lateinit var authoritiesRepository: AuthoritiesRepository

    @InjectMocks
    lateinit var authoritiesService: AuthoritiesService

    @Test
    fun `사용자 권한 등록 성공`(){
        // Given
        val authoritiesSaveDto = CreateAuthoritiesDto.authoritiesSaveDto()
        val findUserEntity = CreateUserEntity.findSuccessCreate()
        val findAuthorityEntity = CreateAuthoritiesEntity.findAuthorityEntity()
        val savedAuthoritiesEntity = CreateAuthoritiesEntity.authoritiesEntity(findUserEntity, findAuthorityEntity)
        val findAuthoritiesEntity = CreateAuthoritiesEntity.findAuthoritiesEntity(findUserEntity, findAuthorityEntity)
        given(userRepository.findByIdAndDeleteFlag(1L, CommonEnum.FlagYn.N))
                .willReturn(Optional.of(findUserEntity))
        given(authorityRepository.findById(1L))
                .willReturn(Optional.of(findAuthorityEntity))
        given(authoritiesRepository.save(any(AuthoritiesEntity::class.java)))
                .willReturn(findAuthoritiesEntity)

        // When
        authoritiesService.saveAuthorities(authoritiesSaveDto)

        // Then
        verify(authoritiesRepository).save(any(AuthoritiesEntity::class.java))
    }
}