package com.yoong.projectyoongbackend.domain.issue.reply.dto

import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import com.yoong.projectyoongbackend.domain.issue.reply.entity.Reply
import java.time.LocalDateTime

data class ReplyResponse(
    val id: Long,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
){
    companion object {
        fun from(reply: Reply): ReplyResponse{
            return ReplyResponse(
                id= reply.id!!,
                description = reply.description,
                createdAt = reply.createdAt,
                updatedAt = reply.updatedAt,
            )
        }
    }
}
