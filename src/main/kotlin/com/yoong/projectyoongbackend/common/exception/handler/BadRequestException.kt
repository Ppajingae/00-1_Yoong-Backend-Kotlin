package com.yoong.projectyoongbackend.common.exception.handler

data class BadRequestException(
    val code: Int = 400,
    val msg: String
): RuntimeException(msg)