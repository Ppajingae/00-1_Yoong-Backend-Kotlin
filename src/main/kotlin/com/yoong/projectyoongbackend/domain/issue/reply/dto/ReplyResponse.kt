package com.yoong.projectyoongbackend.domain.issue.reply.dto

import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import java.time.LocalDateTime

data class ReplyResponse(
    val id: Long,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val issueName: String,
    val nickName: String,
)
