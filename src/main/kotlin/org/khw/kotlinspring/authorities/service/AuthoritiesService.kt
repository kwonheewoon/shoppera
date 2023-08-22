package org.khw.kotlinspring.authorities.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesViewApiDto
import org.khw.kotlinspring.authorities.domain.entity.AuthoritiesEntityFactory
import org.khw.kotlinspring.authorities.domain.mapper.AuthoritiesMapper
import org.khw.kotlinspring.authorities.repository.AuthoritiesRepository
import org.khw.kotlinspring.authorities.repository.AuthorityRepository
import org.khw.kotlinspring.common.enums.CommonEnum.*
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.AuthoritiesException
import org.khw.kotlinspring.common.exception.UserException
import org.khw.kotlinspring.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthoritiesService(val authoritiesRepository: AuthoritiesRepository,
                         val authorityRepository: AuthorityRepository,
                         val authoritiesMapper: AuthoritiesMapper,
        val userRepository: UserRepository) {

    /**
     * 유저의 권한 등록
     *
     * @param authoritiesSaveDto 권한 저장 DTO
     * @return Unit
     */
    fun saveAuthorities(authoritiesSaveDto: AuthoritiesSaveDto): AuthoritiesViewApiDto{
        val findUserEntity = userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        val findAuthorityEntity = authorityRepository.findById(authoritiesSaveDto.authorityId).orElseThrow { AuthoritiesException(ResCode.NOT_FOUND_AUTHORITY)}

        return authoritiesMapper.entityToViewApiDto(authoritiesRepository.save(AuthoritiesEntityFactory.createAuthoritiesEntity(findUserEntity, findAuthorityEntity)))
    }

    /**
     * 유저의 권한 삭제
     *
     * @param userId 유저 아이디(PK)
     * @return Unit
     */
    fun deleteAuthorities(authoritiesId: Long, userId: Long) {
        val findUserEntity = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        val findAuthoritiesEntity = authoritiesRepository.findByIdAndUserAndDeleteFlag(authoritiesId, findUserEntity, FlagYn.N).orElseThrow { AuthoritiesException(ResCode.NOT_FOUND_AUTHORITIES) }
        findAuthoritiesEntity.delete()
    }
}