package com.yoong.projectyoongbackend.domain.issue.issue.dto

import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import com.yoong.projectyoongbackend.domain.issue.issue.repository.projectionDto.FindAllByTeamIdDto
import java.time.LocalDateTime

data class IssuePageResponse(
    val title: String,
    val important: Important,
    val workingStatus: WorkingStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
){
    companion object {
        fun from(findAllByTeamIdDto: FindAllByTeamIdDto): IssuePageResponse {
            return IssuePageResponse(
                title = findAllByTeamIdDto.title,
                important = findAllByTeamIdDto.important,
                workingStatus = findAllByTeamIdDto.workingStatus,
                createdAt = findAllByTeamIdDto.createdAt,
                updatedAt = findAllByTeamIdDto.updatedAt,
            )
        }
    }
}