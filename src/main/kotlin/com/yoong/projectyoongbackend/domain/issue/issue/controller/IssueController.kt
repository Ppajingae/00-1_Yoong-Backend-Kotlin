package com.yoong.projectyoongbackend.domain.issue.issue.controller

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.issue.issue.dto.*
import com.yoong.projectyoongbackend.domain.issue.issue.service.IssueService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issue")
class IssueController(
    private val issueService: IssueService
){

    @PostMapping
    fun createIssue(
         @RequestBody createIssueDto: CreateIssueDto
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.createIssue(createIssueDto))

    @GetMapping("/{issueId}")
    fun getIssue(
        @PathVariable issueId: Long
    ): ResponseEntity<IssueResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.getIssue(issueId))

    @GetMapping
    fun getIssuePage(): ResponseEntity<Page<IssueResponse>>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.getIssuePage())


    @PutMapping("/{issueId}")
    fun updateIssue(
        @PathVariable issueId: Long,
        @RequestBody updateIssueDto: UpdateIssueDto
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(issueService.updateIssue(issueId, updateIssueDto))

    @DeleteMapping("/{issueId}")
    fun deleteIssue(
        @PathVariable issueId: Long
    ): ResponseEntity<DefaultResponse>
    = ResponseEntity.status(HttpStatus.OK).body(issueService.deleteIssue(issueId))

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