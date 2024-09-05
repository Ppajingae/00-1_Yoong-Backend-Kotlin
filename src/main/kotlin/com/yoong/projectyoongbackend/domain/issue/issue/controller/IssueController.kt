package com.yoong.projectyoongbackend.domain.issue.issue.controller

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.issue.issue.dto.*
import com.yoong.projectyoongbackend.domain.issue.issue.service.IssueService
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
@RequestMapping("/api/v1/issue")
class IssueController(
    private val issueService: IssueService
){

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @PostMapping
    fun createIssue(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody createIssueDto: CreateIssueDto
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.createIssue(userPrincipal.id, createIssueDto))

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @GetMapping("/{issueId}")
    fun getIssue(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable issueId: Long
    ): ResponseEntity<IssueResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.getIssue(userPrincipal.id, issueId))

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @GetMapping
    fun getIssuePage(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<IssuePageResponse>>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.getIssuePage(userPrincipal.id, pageable))

    @PreAuthorize("@userSecurityService.hasAllPosition(principal)")
    @PutMapping("/{issueId}")
    fun updateIssue(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable issueId: Long,
        @RequestBody updateIssueDto: UpdateIssueDto
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.updateIssue(userPrincipal.id, issueId, updateIssueDto))

    @PreAuthorize("@userSecurityService.hasLeaderPosition(principal) or @userSecurityService.hasManagerPosition(principal)")
    @DeleteMapping("/{issueId}")
    fun deleteIssue(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable issueId: Long
    ): ResponseEntity<DefaultResponse>
    = ResponseEntity.status(HttpStatus.OK).body(issueService.deleteIssue(userPrincipal.id, issueId))

    @PatchMapping("/{issueId}/important")
    fun changeIssueImportant(
        @PathVariable issueId: Long,
        @RequestBody requestImportant: RequestImportant
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.changeIssueImportant(issueId, requestImportant))

    @PatchMapping("/{issueId}/working-status")
    fun changeIssueWorkingStatus(
        @PathVariable issueId: Long,
        @RequestBody requestWorkingStatus: RequestWorkingStatus
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(issueService.changeIssueWorkingStatus(issueId, requestWorkingStatus))
}