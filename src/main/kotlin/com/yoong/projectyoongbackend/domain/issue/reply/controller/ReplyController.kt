package com.yoong.projectyoongbackend.domain.issue.reply.controller

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.issue.reply.dto.CreateReplyDto
import com.yoong.projectyoongbackend.domain.issue.reply.dto.ReplyResponse
import com.yoong.projectyoongbackend.domain.issue.reply.dto.UpdateReplyDto
import com.yoong.projectyoongbackend.domain.issue.reply.service.ReplyService
import com.yoong.projectyoongbackend.infra.jwt.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/reply")
class ReplyController(
    private val replyService: ReplyService
){

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @PostMapping
    fun createReply(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody createReplyDto: CreateReplyDto
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.CREATED).body(replyService.createReply(userPrincipal.id, createReplyDto))

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @GetMapping()
    fun getMyReply(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<ReplyResponse>>
        = ResponseEntity.status(HttpStatus.OK).body(replyService.getMyReply(userPrincipal.id, pageable))

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable replyId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody updateReplyDto: UpdateReplyDto
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(replyService.updateReply(userPrincipal.id, replyId, updateReplyDto))

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @PathVariable replyId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ):ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(replyService.deleteReply(userPrincipal.id, replyId))
}