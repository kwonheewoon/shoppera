package org.khw.shoppera.authorities.service

import lombok.RequiredArgsConstructor
import org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.shoppera.authorities.domain.dto.AuthoritiesViewApiDto
import org.khw.shoppera.authorities.domain.entity.AuthoritiesEntityFactory
import org.khw.shoppera.authorities.domain.mapper.AuthoritiesMapper
import org.khw.shoppera.authorities.repository.AuthoritiesRepository
import org.khw.shoppera.authorities.repository.AuthorityRepository
import org.khw.shoppera.common.enums.CommonEnum.*
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.AuthoritiesException
import org.khw.shoppera.common.exception.UserException
import org.khw.shoppera.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
    fun saveAuthorities(authoritiesSaveDto: org.khw.shoppera.authorities.domain.dto.AuthoritiesSaveDto): AuthoritiesViewApiDto{
        val findUserEntity = userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        val findAuthorityEntity = authorityRepository.findById(authoritiesSaveDto.authorityId).orElseThrow { AuthoritiesException(ResCode.NOT_FOUND_AUTHORITY)}

        if(authoritiesRepository.countByUserAndAuthorityAndDeleteFlag(findUserEntity, findAuthorityEntity, FlagYn.N) > 0){
            throw AuthoritiesException(ResCode.DUPLICATE_AUTHORITIES)
        }

        return authoritiesMapper.entityToViewApiDto(authoritiesRepository.save(AuthoritiesEntityFactory.createAuthoritiesEntity(findUserEntity, findAuthorityEntity)))
    }

    /**
     * 유저의 권한 삭제
     *
     * @param userId 유저 아이디(PK)
     * @return Unit
     */

    @Transactional
    fun deleteAuthorities(authoritiesId: Long, userId: Long) {
        val findUserEntity = userRepository.findByIdAndDeleteFlag(userId, FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        val findAuthoritiesEntity = authoritiesRepository.findByIdAndUserAndDeleteFlag(authoritiesId, findUserEntity, FlagYn.N).orElseThrow { AuthoritiesException(ResCode.NOT_FOUND_AUTHORITIES) }
        findAuthoritiesEntity.delete()
    }
}