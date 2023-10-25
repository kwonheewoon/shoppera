package org.khw.shoppera.config

import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountAwareAudit : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of(SecurityContextHolder.getContext().authentication.name)
    }

}