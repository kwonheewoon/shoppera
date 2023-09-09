package org.khw.kotlinspring.config.security

import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

import org.springframework.stereotype.Component
import java.util.*

@Component
@RequiredArgsConstructor
class CustomAuthenticationProvider(
    val customUserDetailsService: CustomUserDetailsService,
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    val sCryptPasswordEncoder: SCryptPasswordEncoder
) : AuthenticationProvider{
    override fun authenticate(authentication: Authentication): Authentication {

        val username = authentication.name
        val password = authentication
            .credentials
            .toString()

        val user: UserDetails = customUserDetailsService.loadUserByUsername(username)

        return checkPassword(user, password, bCryptPasswordEncoder)
    }

    override fun supports(authenticationType: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authenticationType)
        //return authenticationType == UsernamePasswordAuthenticationToken::class.java
    }

    private fun checkPassword(user: UserDetails,
                              rawPassword: String,
                              encoder: PasswordEncoder): Authentication{
        if(encoder.matches(rawPassword, user.password)){
            return UsernamePasswordAuthenticationToken(
                user.username,
                user.password,
                user.authorities
            )
        }else{
            throw BadCredentialsException("Bad credentials")
        }
    }
}