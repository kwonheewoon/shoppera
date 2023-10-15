package org.khw.shoppera.config.security.filter

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.khw.authhub.config.security.authentication.UsernamePasswordAuthentication
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(@Value("\${jwt.signing.key}") val signingKey: String) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if (request.requestURI.contains("/h2-console")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt: String = request.getHeader("Authorization")

        val key: SecretKey = Keys.hmacShaKeyFor(
            signingKey.toByteArray(StandardCharsets.UTF_8)
        )

        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .body

        val username: String = claims["username"] as String

        val grantedAuthority: GrantedAuthority = SimpleGrantedAuthority("user")
        val auth = UsernamePasswordAuthentication(
            username,
            null,
            listOf(grantedAuthority)
        )
        SecurityContextHolder.getContext()
            .authentication = auth
        filterChain.doFilter(request, response)
    }
}