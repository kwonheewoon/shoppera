package org.khw.kotlinspring.config.security

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.security.web.csrf.CsrfToken
import java.util.logging.Logger

class CsrfTokenLogger : Filter {

    private val logger: Logger = Logger.getLogger(CsrfTokenLogger::class.simpleName)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val any: Any = request.getAttribute("_csrf")
        val token: CsrfToken = any as CsrfToken

        logger.info("CSRF token ${token.token}")

        chain.doFilter(request, response)
    }


}