package com.yoong.projectyoongbackend.domain.auth.member.dto

data class ValidateMemberDto (
    val validId : Long?,
    val validArgument: String,
    val validType: ValidType
)

enum class ValidType {
    USER_ID,
    EMAIL,
    NICKNAME,
}
