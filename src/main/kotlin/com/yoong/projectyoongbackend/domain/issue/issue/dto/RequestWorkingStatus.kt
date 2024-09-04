package com.yoong.projectyoongbackend.domain.issue.issue.dto

import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus

data class RequestWorkingStatus(
    val workingStatus: WorkingStatus
)
