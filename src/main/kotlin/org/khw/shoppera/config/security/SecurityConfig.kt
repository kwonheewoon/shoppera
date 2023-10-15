package org.khw.shoppera.config.security

import lombok.RequiredArgsConstructor
import org.khw.shoppera.config.security.filter.JwtAuthenticationFilter
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@RequiredArgsConstructor
class SecurityConfig(
    val jwtAuthenticationFilter: JwtAuthenticationFilter,
    val customAuthenticationProvider: CustomAuthenticationProvider,
    val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler
) {

    private val PERMIT_URL_ARRAY: Array<String> = arrayOf( /* swagger v2 */
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",  /* swagger v3 */
        "/v3/api-docs/**",
        "/swagger-ui/**"
    )

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

            .requestMatchers(*PERMIT_URL_ARRAY).permitAll()
            .anyRequest().authenticated()
            //.anyRequest().permitAll()
            .and()
            .authenticationManager(authenticationManager)

        return http.build()
    }
}