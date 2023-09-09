package org.khw.kotlinspring.config.security

import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.lang.RuntimeException
import java.util.stream.Collectors

class UserDetails(val userEntity: UserEntity) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return userEntity.authorities?.stream()
            ?.map { a -> SimpleGrantedAuthority(a.authority.authority) }
            ?.collect(Collectors.toList()) ?: throw RuntimeException("권한이 존재하지 않는 계정입니다.")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        return userEntity.name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getUser(): UserEntity{
        return userEntity
    }

}