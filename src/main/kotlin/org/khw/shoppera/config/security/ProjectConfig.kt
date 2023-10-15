package org.khw.shoppera.config.security

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource

@Configuration
class ProjectConfig {

    @Bean
    fun userDetailsService(dataSource: DataSource): UserDetailsService{
        return JdbcUserDetailsManager(dataSource)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun sCryptPasswordEncoder(): SCryptPasswordEncoder {
        return SCryptPasswordEncoder(16384,8,1,32,64)
    }

//    @Bean
//    fun passwordEncoder(): PasswordEncoder{
//        val encoders = HashMap<String, PasswordEncoder> ()
//
//        encoders.put("noop", NoOpPasswordEncoder.getInstance())
//        encoders.put("bcrypt", BCryptPasswordEncoder())
//        encoders.put("scrypt", SCryptPasswordEncoder(16384, 8,1,32,64))
//
//        return DelegatingPasswordEncoder("bcrypt", encoders)
//    }

    // SecurityContextHolder 모드 설정
//    @Bean
//    fun initializingBean(): InitializingBean{

        // MODE_THREADLOCAL, MODE_INHERITEDTHREADLOCAL, MODE_GLOBAL
//        return InitializingBean {SecurityContextHolder.setStrategyName(
//            SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
//        )}
//    }
}