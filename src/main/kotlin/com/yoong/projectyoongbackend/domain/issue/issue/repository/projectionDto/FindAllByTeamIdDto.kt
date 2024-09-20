package com.yoong.projectyoongbackend.domain.issue.issue.repository.projectionDto

import com.querydsl.core.annotations.QueryProjection
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import java.time.LocalDateTime

data class FindAllByTeamIdDto @QueryProjection constructor(
    val title: String,
    val important: Important,
    val workingStatus: WorkingStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)