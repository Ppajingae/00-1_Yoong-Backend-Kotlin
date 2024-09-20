package com.yoong.projectyoongbackend.domain.auth.member.valid_class

import com.yoong.projectyoongbackend.common.exception.handler.ModelNotFoundException
import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidType
import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class AuthDuplicatedValidator {

    fun checkDuplicated(validateMemberDto: ValidateMemberDto, memberRepository: MemberRepository): Long? {

        when(validateMemberDto.validType){
            ValidType.USER_ID -> {
                if(memberRepository.existsByUserId(validateMemberDto.validArgument)) throw ModelNotFoundException(404, "중복되는 값이 존재 합니다")
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



                    return member.id

                }else{
                    val member = memberRepository.findByIdOrNull(validateMemberDto.validId)?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

                    member.updateValid(validateMemberDto.validType, validateMemberDto.validArgument)

                    memberRepository.save(member)

                    return member.id
                }
            }
            ValidType.EMAIL -> {
                if(memberRepository.existsByEmail(validateMemberDto.validArgument)) throw ModelNotFoundException(404, "중복되는 값이 존재 합니다")
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

                    return member.id
                }else{
                    val member = memberRepository.findByIdOrNull(validateMemberDto.validId)?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

                    member.updateValid(validateMemberDto.validType, validateMemberDto.validArgument)

                    memberRepository.save(member)

                    return member.id
                }
            }
            ValidType.NICKNAME -> {
                if(memberRepository.existsByNickname(validateMemberDto.validArgument)) throw ModelNotFoundException(404, "중복되는 값이 존재 합니다")
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

                    return member.id
                }else{
                    val member = memberRepository.findByIdOrNull(validateMemberDto.validId)?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

                    member.updateValid(validateMemberDto.validType, validateMemberDto.validArgument)

                    memberRepository.save(member)

                    return member.id
                }
            }
        }
    }
}