package org.khw.shoppera.common.util

import org.springframework.security.core.context.SecurityContextHolder

class JwtUtil {
    companion object{
        fun getName(): String{
            return SecurityContextHolder.getContext().authentication.name
        }
    }
}