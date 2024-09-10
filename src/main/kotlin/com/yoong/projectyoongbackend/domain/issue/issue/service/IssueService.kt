package com.yoong.projectyoongbackend.domain.issue.issue.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.common.exception.handler.AccessFailedException
import com.yoong.projectyoongbackend.common.exception.handler.ModelNotFoundException
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import com.yoong.projectyoongbackend.domain.issue.issue.dto.*
import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import com.yoong.projectyoongbackend.domain.issue.issue.repository.IssueRepository
import com.yoong.projectyoongbackend.domain.issue.reply.repository.ReplyRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class IssueService(
    private val issueRepository: IssueRepository,
    private val memberRepository: MemberRepository,
    private val replyRepository: ReplyRepository,
){
    @Transactional
    fun createIssue(memberId: Long, createIssueDto: CreateIssueDto): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        if(member.team!!.name == "DUMMY_TEAM") throw AccessFailedException(403, "권한이 없습니다")

        issueRepository.save(
            Issue(
                title = createIssueDto.title,
                description = createIssueDto.description,
                important = createIssueDto.important,
                workingStatus = WorkingStatus.TODO,
                author = member.userId,
                teamId = member.team!!.id!!
            )
        )

        return DefaultResponse.from("이슈 등록이 완료 되었습니다")
    }

    fun getIssue(memberId: Long, issueId: Long): IssueResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ModelNotFoundException(404, "작성된 글이 존재하지 않습니다")

        val replyList = replyRepository.findAllByIssueId(issueId)

        if(member.team!!.id != issue.teamId) throw AccessFailedException(403, "권한이 없습니다")

        return IssueResponse.from(issue, replyList)
    }

    fun getIssuePage(memberId: Long, pageable: Pageable): Page<IssuePageResponse> {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issuePage = issueRepository.findAllByTeamId(member.team!!.id!!, pageable)

        return issuePage.map { IssuePageResponse.from(it) }
    }

    @Transactional
    fun updateIssue(memberId: Long, issueId: Long, updateIssueDto: UpdateIssueDto): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ModelNotFoundException(404, "작성된 글이 존재하지 않습니다")

        // 같은 팀일 경우 수정 가능
        if(member.team!!.id!! != issue.teamId) throw AccessFailedException(403, "수정 권한이 없습니다")

        issue.apply {
            title = updateIssueDto.title
            description = updateIssueDto.description
            updatedAt = LocalDateTime.now()
        }

        return DefaultResponse.from("이슈 업데이트가 완료 되었습니다")
    }

    @Transactional
    fun deleteIssue(memberId: Long, issueId: Long): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ModelNotFoundException(404, "작성된 글이 존재하지 않습니다")

        if(member.team!!.id!! != issue.teamId) throw AccessFailedException(403, "삭제 권한이 없습니다")

        issue.apply{
            isDeleted = true
            deletedAt = LocalDateTime.now()
        }

        return DefaultResponse.from("이슈 삭제가 완료 되었습니다")
    }

    @Transactional
    fun changeIssueImportant(memberId: Long, issueId: Long, requestImportant: RequestImportant): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ModelNotFoundException(404, "작성된 글이 존재하지 않습니다")

        if(member.team!!.id!! != issue.teamId) throw AccessFailedException(403, "중요도 변경 권한이 없습니다")

        issue.apply {
            important = requestImportant.important
        }

        return DefaultResponse.from("중요도 변경이 완료 되었습니다")
    }

    @Transactional
    fun changeIssueWorkingStatus(memberId: Long, issueId: Long, requestWorkingStatus: RequestWorkingStatus): DefaultResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(404, "맴버가 존재 하지 않습니다")

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw ModelNotFoundException(404, "작성된 글이 존재하지 않습니다")

        if(member.team!!.id!! != issue.teamId) throw AccessFailedException(403, "진행 상황 변경 권한이 없습니다")

        issue.apply {
            workingStatus = requestWorkingStatus.workingStatus
        }

        return DefaultResponse.from("진행 상황 변경이 완료 되었습니다")
    }
}