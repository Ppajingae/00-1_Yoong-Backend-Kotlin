package com.yoong.projectyoongbackend.domain.auth.member.controller

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.auth.member.dto.CreateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.MemberLoginDto
import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidateMemberDto
import com.yoong.projectyoongbackend.domain.auth.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/sign-up")
    fun signUp(
        @RequestBody createMemberDto: CreateMemberDto
    ): ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(memberService.signUp(createMemberDto))


    @PostMapping("/login")
    fun login(
        @RequestBody memberLoginDto: MemberLoginDto
    ): ResponseEntity<DefaultResponse> =
        ResponseEntity.status(HttpStatus.OK).body(memberService.login(memberLoginDto))

    @PostMapping("/sign-up/valid")
    fun duplicateValidate(
        @RequestBody validateMemberDto: ValidateMemberDto): ResponseEntity<DefaultResponse> =
        ResponseEntity.status(HttpStatus.NO_CONTENT).body(memberService.duplicateValidate(validateMemberDto))
}