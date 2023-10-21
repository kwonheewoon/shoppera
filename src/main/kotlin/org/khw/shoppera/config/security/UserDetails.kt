package org.khw.shoppera.config.security

import org.khw.shoppera.user.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.lang.RuntimeException
import java.util.stream.Collectors

class UserDetails(val users: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return users.authorities?.stream()
            ?.map { a -> SimpleGrantedAuthority(a.authority.authority) }
            ?.collect(Collectors.toList()) ?: throw RuntimeException("권한이 존재하지 않는 계정입니다.")
    }

    override fun getPassword(): String {
        return users.password
    }

    override fun getUsername(): String {
        return users.name
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

    fun getUser(): User{
        return users
    }

}