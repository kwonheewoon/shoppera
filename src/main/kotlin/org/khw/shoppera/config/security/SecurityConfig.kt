package org.khw.shoppera.config.security

import lombok.RequiredArgsConstructor
import org.khw.shoppera.config.security.filter.JwtAuthenticationFilter
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CsrfFilter

@Configuration
@RequiredArgsConstructor
class SecurityConfig(
    val jwtAuthenticationFilter: JwtAuthenticationFilter,
    val customAuthenticationProvider: CustomAuthenticationProvider,
    val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{

        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)

        //authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider)
        val authenticationManager = authenticationManagerBuilder.build();

        http.csrf().disable()
        http.headers().frameOptions().disable()


//        http.formLogin()
//            .defaultSuccessUrl("/home", true)
//            .successHandler(customAuthenticationSuccessHandler)
//            .failureHandler(customAuthenticationFailureHandler)
//            .and()
//        http.httpBasic { c ->
//            c.realmName("OTHER")
//            c.authenticationEntryPoint(CustomEntryPoint())
//        }



        http
            .addFilterAt(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated()
            //.anyRequest().permitAll()
            .and()
            .authenticationManager(authenticationManager)

        return http.build()
    }
}