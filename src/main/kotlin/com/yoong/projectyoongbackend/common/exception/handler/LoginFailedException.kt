package com.yoong.projectyoongbackend.common.exception.handler

data class LoginFailedException(
    val msg: String,
): RuntimeException(msg)