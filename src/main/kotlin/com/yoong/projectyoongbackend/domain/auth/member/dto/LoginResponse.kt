package com.yoong.projectyoongbackend.domain.auth.member.dto

import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role

data class LoginResponse (
    val role: Role,
    val position: Position,
    val accessToken: String,
){
    companion object{
        fun from(member: Member, accessToken: String): LoginResponse {
            return LoginResponse(
                role = member.role,
                position = member.position,
                accessToken = accessToken
            )
        }
    }
}