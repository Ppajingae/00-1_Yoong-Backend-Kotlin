package com.yoong.projectyoongbackend.domain.auth.member.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.auth.member.dto.CreateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.MemberLoginDto
import org.springframework.stereotype.Service

@Service
class MemberService {
    
    fun signUp(createMemberDto: CreateMemberDto): DefaultResponse {

    }

    fun login(memberLoginDto: MemberLoginDto): DefaultResponse {

    }
}