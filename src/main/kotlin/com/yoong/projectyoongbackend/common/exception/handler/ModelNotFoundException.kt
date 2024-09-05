package com.yoong.projectyoongbackend.common.exception.handler

data class ModelNotFoundException(
    val code: Int,
    val msg: String
): RuntimeException(msg)