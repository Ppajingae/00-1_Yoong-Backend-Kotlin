package com.yoong.projectyoongbackend.domain.auth.member.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.auth.member.dto.CreateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.MemberLoginDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidType
import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
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

        val member = memberRepository.findByIdOrNull(createMemberDto.id) ?: throw RuntimeException("아이디가 존재 하지 않습니다")

        if(member.isId && member.isEmail && member.isNickName) {

            val dummyTeam = teamRepository.findByDummyTeam()

            member.updateProfile(createMemberDto, dummyTeam)

            memberRepository.save(member)

        }else throw RuntimeException("중복 검사를 진행해 주세요")

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

    @Transactional
    fun duplicateValidate(validateMemberDto: ValidateMemberDto): DefaultResponse {

        when(validateMemberDto.validType){
            ValidType.USER_ID -> {
                if(memberRepository.existsByUserId(validateMemberDto.validArgument)) throw RuntimeException("중복 되는 아이디 값이 존재 합니다")
                if(validateMemberDto.validId == null){
                    val member = memberRepository.saveAndFlush(
                        Member(
                            userId = validateMemberDto.validArgument,
                            password = "",
                            email = "",
                            nickname = "",
                            role = Role.MEMBER,
                            position = Position.MEMBER,
                            team = null
                        )
                    )

                    member.apply { isId = true }
                }else{
                    val member = memberRepository.findByIdOrNull(validateMemberDto.validId)?: throw RuntimeException("맴버가 존재 하지 않습니다")

                    member.updateValid(validateMemberDto.validType, validateMemberDto.validArgument)

                    memberRepository.save(member)
                }
            }
            ValidType.EMAIL -> {
                if(memberRepository.existsByEmail(validateMemberDto.validArgument)) throw RuntimeException("중복 되는 이메일 값이 존재 합니다")
                if(validateMemberDto.validId == null){
                    val member = memberRepository.saveAndFlush(
                        Member(
                            userId = "",
                            password = "",
                            email = validateMemberDto.validArgument,
                            nickname = "",
                            role = Role.MEMBER,
                            position = Position.MEMBER,
                            team = null
                        )
                    )

                    member.apply { isEmail = true }
                }else{
                    val member = memberRepository.findByIdOrNull(validateMemberDto.validId)?: throw RuntimeException("맴버가 존재 하지 않습니다")

                    member.updateValid(validateMemberDto.validType, validateMemberDto.validArgument)

                    memberRepository.save(member)
                }
            }
            ValidType.NICKNAME -> {
                if(memberRepository.existsByNickname(validateMemberDto.validArgument)) throw RuntimeException("중복 되는 닉네임 값이 존재 합니다")
                if(validateMemberDto.validId == null){
                    val member = memberRepository.saveAndFlush(
                        Member(
                            userId = "",
                            password = "",
                            email = "",
                            nickname = validateMemberDto.validArgument,
                            role = Role.MEMBER,
                            position = Position.MEMBER,
                            team = null
                        )
                    )

                    member.apply { isNickName = true }
                }else{
                    val member = memberRepository.findByIdOrNull(validateMemberDto.validId)?: throw RuntimeException("맴버가 존재 하지 않습니다")

                    member.updateValid(validateMemberDto.validType, validateMemberDto.validArgument)

                    memberRepository.save(member)
                }
            }
        }

        return DefaultResponse.from("중복 확인 완료 되었습니다")
    }
}