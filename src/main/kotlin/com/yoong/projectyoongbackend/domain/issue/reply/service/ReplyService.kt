package com.yoong.projectyoongbackend.domain.issue.reply.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.common.exception.handler.AccessFailedException
import com.yoong.projectyoongbackend.common.exception.handler.ModelNotFoundException
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import com.yoong.projectyoongbackend.domain.issue.issue.repository.IssueRepository
import com.yoong.projectyoongbackend.domain.issue.reply.dto.CreateReplyDto
import com.yoong.projectyoongbackend.domain.issue.reply.dto.ReplyResponse
import com.yoong.projectyoongbackend.domain.issue.reply.dto.UpdateReplyDto
import com.yoong.projectyoongbackend.domain.issue.reply.entity.Reply
import com.yoong.projectyoongbackend.domain.issue.reply.repository.ReplyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ReplyService(
    private val memberRepository: MemberRepository,
    private val replyRepository: ReplyRepository,
    private val issueRepository: IssueRepository
){

    @Transactional
    fun createReply(memberId: Long, createReplyDto: CreateReplyDto): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue = issueRepository.findByIdOrNull(createReplyDto.issueId) ?: throw ModelNotFoundException(404, "이슈가 존재 하지 않습니다")

        replyRepository.save(
            Reply(
                description = createReplyDto.description,
                issue = issue,
                member = member,
            )
        )

        return DefaultResponse.from("댓글 작성이 완료 되었습니다")
    }

    fun getMyReply(memberId: Long, pageable: Pageable): Page<ReplyResponse> {

        val replyPage = replyRepository.findAllByMyIssue(memberId, pageable)

        return replyPage.map { ReplyResponse.from(it) }
    }

    @Transactional
    fun updateReply(memberId: Long, replyId: Long, updateReplyDto: UpdateReplyDto): DefaultResponse {

        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ModelNotFoundException(404, "댓글이 존재 하지 않습니다")

        if(reply.member.id != memberId) throw AccessFailedException(403, "수정 권한이 없습니다")

        reply.apply {
            description = updateReplyDto.description
        }

        return DefaultResponse.from("댓글 수정이 완료 되었습니다")
    }

    @Transactional
    fun deleteReply(memberId: Long, replyId: Long): DefaultResponse {

        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ModelNotFoundException(404, "댓글이 존재 하지 않습니다")

        if(reply.member.id != memberId) throw AccessFailedException(403, "삭제 권한이 없습니다")

        reply.apply {
            deletedAt = LocalDateTime.now()
            isDeleted = true
        }

        return DefaultResponse.from("댓글 삭제가 완료 되었습니다")
    }
}