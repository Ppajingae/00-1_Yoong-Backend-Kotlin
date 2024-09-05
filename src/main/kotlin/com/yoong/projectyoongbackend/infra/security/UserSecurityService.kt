package com.yoong.projectyoongbackend.infra.security

import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.infra.jwt.UserPrincipal
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class UserSecurityService {

    fun hasLeaderPosition(principal: Any): Boolean {
        val userPrincipal = principal as UserPrincipal
        return userPrincipal.positionAuthorities.contains(SimpleGrantedAuthority("POSITION_LEADER"))
    }

    fun hasManagerPosition(principal: Any): Boolean {
        val userPrincipal = principal as UserPrincipal
        return userPrincipal.positionAuthorities.contains(SimpleGrantedAuthority("POSITION_MANAGER"))
    }

    fun hasMemberPosition(principal: Any): Boolean {
        val userPrincipal = principal as UserPrincipal
        return userPrincipal.positionAuthorities.contains(SimpleGrantedAuthority("POSITION_MEMBER"))
    }

    fun hasAllPosition(principal: Any): Boolean {
        val userPrincipal = principal as UserPrincipal
        return userPrincipal.positionAuthorities.isNotEmpty()
    }
}