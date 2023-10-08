package org.khw.authhub.config.security.authentication

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UsernamePasswordAuthentication: UsernamePasswordAuthenticationToken {

    constructor(principal: Any, credentials: Any?, authorities: Collection<out GrantedAuthority>) : super(principal, credentials, authorities)

    constructor(principal: Any, credentials: Any) : super(principal, credentials)


}