package com.yoong.projectyoongbackend.common.exception.handler

data class AccessFailedException(
    val code: Int,
    val msg: String,
): RuntimeException(msg)