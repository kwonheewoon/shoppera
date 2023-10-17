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


    private val PERMIT_URL_LIST = listOf(
        "/h2-console",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/",  /* swagger v3 */
        "/v3/api-docs/",
        "/swagger-ui/"
    )

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if (PERMIT_URL_LIST.any{request.requestURI.contains(it)}) {
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