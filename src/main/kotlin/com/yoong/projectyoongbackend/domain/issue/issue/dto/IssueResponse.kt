package com.yoong.projectyoongbackend.domain.issue.issue.dto

import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import com.yoong.projectyoongbackend.domain.issue.reply.dto.ReplyResponse
import com.yoong.projectyoongbackend.domain.issue.reply.entity.Reply
import java.time.LocalDateTime
import java.util.*

data class IssueResponse(
    val author: String,
    val title: String,
    val description: String,
    val important: Important,
    val workingStatus: WorkingStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val reply: List<ReplyResponse>
){
    companion object {
        fun from(issue: Issue, replyList: List<Reply>): IssueResponse{
            return IssueResponse(
                author = issue.author,
                title = issue.title,
                description = issue.description,
                important = issue.important,
                workingStatus = issue.workingStatus,
                createdAt = issue.createdAt,
                updatedAt = issue.updatedAt,
                reply = replyList.map { ReplyResponse.from(it) }
            )
        }
    }
}