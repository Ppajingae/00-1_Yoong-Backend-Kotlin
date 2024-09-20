package com.yoong.projectyoongbackend.domain.auth.member.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.common.exception.handler.DuplicatedException
import com.yoong.projectyoongbackend.common.exception.handler.LoginFailedException
import com.yoong.projectyoongbackend.common.exception.handler.ModelNotFoundException
import com.yoong.projectyoongbackend.domain.auth.member.dto.CreateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.LoginResponse
import com.yoong.projectyoongbackend.domain.auth.member.dto.MemberLoginDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import com.yoong.projectyoongbackend.domain.auth.member.valid_class.AuthDuplicatedValidator
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
    private val authDuplicatedValidator: AuthDuplicatedValidator
){

    private val passwordEncoder = PasswordEncoder().bCryptPasswordEncoder()

    @Transactional
    fun signUp(createMemberDto: CreateMemberDto): DefaultResponse {

        val member = memberRepository.findByIdOrNull(createMemberDto.id) ?: throw ModelNotFoundException(404, "아이디가 존재 하지 않습니다")

        if(member.isId && member.isEmail && member.isNickName) {

            val dummyTeam = teamRepository.findByDummyTeam()

            val password = passwordEncoder.encode(createMemberDto.password)

            member.updateProfile(password, dummyTeam)

            memberRepository.save(member)

        }else throw DuplicatedException(400, "중복 검사를 진행해 주세요")

        return DefaultResponse.from("회원 가입이 완료 되었습니다")
    }

    fun login(memberLoginDto: MemberLoginDto): LoginResponse {

        val member = memberRepository.findByUserId(memberLoginDto.id) ?: throw ModelNotFoundException(404, "존재 하지 않는 유저 입니다")

        if (passwordEncoder.matches(memberLoginDto.password, member.password)) {
            val token = jwtPlugin.generateAccessToken(
                subject = member.id.toString(),
                email = member.email,
                role = member.role.name,
                position = member.position.name)
            return LoginResponse.from(member, token)
        }

        throw LoginFailedException("로그인에 실패 하였습니다!! 다시 로그인 해 주세요")
    }

    @Transactional
    fun duplicateValidate(validateMemberDto: ValidateMemberDto): DefaultResponse {

        var memberId: Long? = 0


        memberId = authDuplicatedValidator.checkDuplicated(validateMemberDto, memberRepository)

        if(memberId == null) throw ModelNotFoundException(404, "아이디가 존재하지 않습니다")



        return if(validateMemberDto.validId == null) {
            DefaultResponse.from("$memberId")
        }else{
            DefaultResponse.from("중복 확인 완료 되었습니다")
        }
    }
}