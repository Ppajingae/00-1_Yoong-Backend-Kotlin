package com.yoong.projectyoongbackend.domain.auth.member.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.auth.member.dto.CreateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.MemberLoginDto
import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import com.yoong.projectyoongbackend.domain.auth.team.entity.Team
import com.yoong.projectyoongbackend.domain.auth.team.repository.TeamRepository
import com.yoong.projectyoongbackend.infra.jwt.JwtPlugin
import com.yoong.projectyoongbackend.infra.jwt.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val jwtPlugin: JwtPlugin,
    private val teamRepository: TeamRepository,
){

    private val passwordEncoder = PasswordEncoder().bCryptPasswordEncoder()

    @Transactional
    fun signUp(createMemberDto: CreateMemberDto): DefaultResponse {

        val dummyTeam = teamRepository.findByDummyTeam()

        memberRepository.save(
            Member(
                userId = createMemberDto.userId,
                password = passwordEncoder.encode(createMemberDto.password),
                nickName = createMemberDto.nickname,
                email = createMemberDto.email,
                role = Role.MEMBER,
                position = Position.MEMBER,
                team = dummyTeam
            )
        )

        return DefaultResponse.from("회원 가입이 완료 되었습니다")
    }

    fun login(memberLoginDto: MemberLoginDto): DefaultResponse {

        val member = memberRepository.findByUserId(memberLoginDto.id) ?: throw RuntimeException("존재 하지 않는 유저 입니다")

        if (passwordEncoder.matches(memberLoginDto.password, member.password)) {
            val token = jwtPlugin.generateAccessToken(
                subject = member.id.toString(),
                email = member.email,
                role = member.role.name,
                position = member.position.name)
            return DefaultResponse(token)
        }

        throw RuntimeException("로그인에 실패 하였습니다!! 다시 로그인 해 주세요")
    }
}