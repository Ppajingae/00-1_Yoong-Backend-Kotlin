package com.yoong.projectyoongbackend.domain.issue.issue.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.issue.issue.dto.*
import com.yoong.projectyoongbackend.domain.issue.issue.repository.IssueRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page

@Service
class IssueService(
    private val issueRepository: IssueRepository,
){

    fun createIssue(createIssueDto: CreateIssueDto): DefaultResponse {
        TODO()
    }

    fun getIssue(issueId: Long): IssueResponse {
        TODO()
    }

    fun getIssuePage(): Page<IssueResponse> {
        TODO()
    }

    fun updateIssue(issueId: Long, updateIssueDto: UpdateIssueDto): DefaultResponse {
        TODO()
    }

    fun deleteIssue(issueId: Long): DefaultResponse {
        TODO()
    }

    fun changeIssueImportant(issueId: Long, requestImportant: RequestImportant): DefaultResponse {
        TODO()
    }

    fun changeIssueWorkingStatus(issueId: Long, requestWorkingStatus: RequestWorkingStatus): DefaultResponse {
        TODO()
    }
}