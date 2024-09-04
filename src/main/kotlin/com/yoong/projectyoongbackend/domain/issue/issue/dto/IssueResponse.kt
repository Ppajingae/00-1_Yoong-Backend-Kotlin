package com.yoong.projectyoongbackend.domain.issue.issue.dto

import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import java.time.LocalDateTime
import java.util.*

data class IssueResponse(
    val author: String,
    val title: String,
    val description: String,
    val important: Important,
    val workingStatus: WorkingStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)