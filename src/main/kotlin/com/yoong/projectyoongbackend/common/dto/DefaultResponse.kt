package com.yoong.projectyoongbackend.common.dto

data class DefaultResponse(
    val msg: String,
){
    companion object {
        fun from (message: String): DefaultResponse = DefaultResponse(
            msg = message
        )
    }
}
