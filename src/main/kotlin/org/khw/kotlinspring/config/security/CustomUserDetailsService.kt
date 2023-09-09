package org.khw.kotlinspring.config.security

import lombok.RequiredArgsConstructor
import org.khw.kotlinspring.common.enums.CommonEnum.FlagYn
import org.khw.kotlinspring.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val findUserEntity = userRepository.findByAccountIdAndDeleteFlag(username, FlagYn.N)
            .orElseThrow { UsernameNotFoundException("존재하지 않는 계정 정보 입니다.") }

        return UserDetails(findUserEntity)
    }
}