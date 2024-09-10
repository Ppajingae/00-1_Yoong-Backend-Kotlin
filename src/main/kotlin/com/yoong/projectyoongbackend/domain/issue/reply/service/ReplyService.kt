package com.yoong.projectyoongbackend.domain.issue.reply.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.common.exception.handler.ModelNotFoundException
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import com.yoong.projectyoongbackend.domain.issue.issue.repository.IssueRepository
import com.yoong.projectyoongbackend.domain.issue.reply.dto.CreateReplyDto
import com.yoong.projectyoongbackend.domain.issue.reply.dto.ReplyResponse
import com.yoong.projectyoongbackend.domain.issue.reply.dto.UpdateReplyDto
import com.yoong.projectyoongbackend.domain.issue.reply.repository.ReplyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReplyService(
    private val memberRepository: MemberRepository,
    private val replyRepository: ReplyRepository,
    private val issueRepository: IssueRepository
){

    @Transactional
    fun createReply(memberId: Long, createReplyDto: CreateReplyDto): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue =
    }

    fun getMyReply(memberId: Long, pageable: Pageable): Page<ReplyResponse> {
        TODO()
    }

    fun updateReply(memberId: Long, replyId: Long, updateReplyDto: UpdateReplyDto): DefaultResponse {
        TODO("Not yet implemented")
    }

    fun deleteReply(id: Long, replyId: Long): DefaultResponse {
        TODO()
    }
}