package com.yoong.projectyoongbackend.domain.issue.reply.dto

data class CreateReplyDto(
    val issueId: Long,
    val description: String
)
