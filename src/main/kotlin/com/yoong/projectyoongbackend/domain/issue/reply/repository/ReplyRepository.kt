package com.yoong.projectyoongbackend.domain.issue.reply.repository

import com.yoong.projectyoongbackend.domain.issue.reply.entity.Reply
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ReplyRepository {
}

interface ReplyJpaRepository: JpaRepository<Reply, Long>

@Repository
class ReplyRepositoryImpl(
    private val replyJpaRepository: ReplyJpaRepository
)