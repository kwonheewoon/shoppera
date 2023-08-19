package org.khw.kotlinspring.common.security

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@RequiredArgsConstructor
class SecurityConfig(
    val customAuthenticationProvider: CustomAuthenticationProvider
) {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{

        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
//        authenticationManagerBuilder.inMemoryAuthentication()
//            .withUser("john")
//            .password("12345")
//            .authorities("read")
//            .and()
//            .passwordEncoder(NoOpPasswordEncoder.getInstance())
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider)
        val authenticationManager = authenticationManagerBuilder.build();

        http.httpBasic()
        http.authorizeHttpRequests()
            //.anyRequest().authenticated()
            .anyRequest().authenticated()
            .and()
            .authenticationManager(authenticationManager)

        return http.build()
    }
}