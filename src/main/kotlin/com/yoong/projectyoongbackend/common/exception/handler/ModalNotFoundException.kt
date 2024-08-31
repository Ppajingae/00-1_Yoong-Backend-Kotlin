package com.yoong.projectyoongbackend.common.exception.handler

data class ModalNotFoundException(
    val code: Int,
    val msg: String
): RuntimeException(msg)