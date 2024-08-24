package com.yoong.projectyoongbackend.infra.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val email: String,
    val authorities: Collection<GrantedAuthority>,
    val positionAuthorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, email: String, roles: Set<String>, positions: Set<String>) : this(
        id,
        email,
        roles.map { SimpleGrantedAuthority("ROLE_$it") },
        positions.map { SimpleGrantedAuthority("POSITION_$it") }
    )
}