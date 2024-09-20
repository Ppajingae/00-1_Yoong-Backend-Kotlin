package com.yoong.projectyoongbackend.domain.issue.issue.dto

import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important

data class RequestImportant(
    val important: Important
)