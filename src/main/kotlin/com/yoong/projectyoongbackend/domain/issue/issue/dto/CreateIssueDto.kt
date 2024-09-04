package com.yoong.projectyoongbackend.domain.issue.issue.dto

import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important

data class CreateIssueDto(
    val title: String,
    val description: String,
    val important: Important
)