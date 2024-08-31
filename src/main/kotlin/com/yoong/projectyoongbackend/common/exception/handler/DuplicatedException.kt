package com.yoong.projectyoongbackend.common.exception.handler

data class DuplicatedException(
    val code: Int,
    val msg: String
) : RuntimeException(msg)