package org.khw.kotlinspring.common.security

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {

        val username = authentication?.name
        val password = authentication?.credentials.toString()

        // provider가 userdetatilsService와 passwordEncoder 대체
        if("john" == username && "12345" == password){
            return UsernamePasswordAuthenticationToken(username, password, listOf())
        } else{
            throw AuthenticationCredentialsNotFoundException("Error in authentication!")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}