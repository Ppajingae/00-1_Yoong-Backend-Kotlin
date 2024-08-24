package com.yoong.projectyoongbackend.domain.auth.member.dto

data class CreateMemberDto(
    val userId: String,
    val email: String,
    val nickname: String,
    val password: String
)