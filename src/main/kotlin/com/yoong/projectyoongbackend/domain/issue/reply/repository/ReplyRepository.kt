package com.yoong.projectyoongbackend.domain.issue.reply.repository

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.projectyoongbackend.domain.issue.reply.entity.QReply
import com.yoong.projectyoongbackend.domain.issue.reply.entity.Reply
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface ReplyRepository {

    fun findByIdOrNull(id: Long): Reply?

    fun save(reply: Reply): Reply

    fun findAllByMyIssue(memberId: Long, pageable: Pageable): Page<Reply>
}

interface ReplyJpaRepository: JpaRepository<Reply, Long>

@Repository
class ReplyRepositoryImpl(
    private val replyJpaRepository: ReplyJpaRepository,
    @PersistenceContext
    private val em : EntityManager
): ReplyRepository {

    private val queryFactory = JPAQueryFactory(em)
    private val reply = QReply.reply

    override fun findByIdOrNull(id: Long): Reply? = replyJpaRepository.findByIdOrNull(id)

    override fun save(reply: Reply): Reply = replyJpaRepository.save(reply)

    override fun findAllByMyIssue(memberId: Long, pageable: Pageable): Page<Reply> {

        val query =  queryFactory.selectFrom(reply)
            .where(reply.member.id.eq(memberId))
            .fetch()


        return PageImpl(query, pageable, query.size.toLong())
    }
}