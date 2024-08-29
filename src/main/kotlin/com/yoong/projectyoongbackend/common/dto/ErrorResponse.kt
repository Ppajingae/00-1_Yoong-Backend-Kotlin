package com.yoong.projectyoongbackend.common.dto

data class ErrorResponse(
    val statusCode: Int,
    val message: String,
)