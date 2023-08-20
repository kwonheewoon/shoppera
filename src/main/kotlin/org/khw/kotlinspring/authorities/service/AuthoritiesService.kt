package org.khw.kotlinspring.authorities.service

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.authorities.domain.dto.AuthoritiesSaveDto
import org.khw.kotlinspring.authorities.domain.entity.AuthoritiesEntity
import org.khw.kotlinspring.authorities.repository.AuthoritiesRepository
import org.khw.kotlinspring.authorities.repository.AuthorityRepository
import org.khw.kotlinspring.common.enums.CommonEnum
import org.khw.kotlinspring.common.enums.ResCode
import org.khw.kotlinspring.common.exception.UserException
import org.khw.kotlinspring.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthoritiesService(val authoritiesRepository: AuthoritiesRepository,
                         val authorityRepository: AuthorityRepository,
        val userRepository: UserRepository) {

    fun saveAuthorities(authoritiesSaveDto: AuthoritiesSaveDto){
        val findUserEntity = userRepository.findByIdAndDeleteFlag(authoritiesSaveDto.userId, CommonEnum.FlagYn.N).orElseThrow { UserException(ResCode.NOT_FOUND_USER) }
        val findAuthorityEntity = authorityRepository.findById(authoritiesSaveDto.authorityId).orElseThrow { IllegalStateException("존재하지 않는 권한 입니다.") }
        authoritiesRepository.save(AuthoritiesEntity(null, findUserEntity, findAuthorityEntity, CommonEnum.FlagYn.N))
    }
}