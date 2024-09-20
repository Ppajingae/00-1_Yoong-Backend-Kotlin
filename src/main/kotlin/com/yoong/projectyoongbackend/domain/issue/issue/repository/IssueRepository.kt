package com.yoong.projectyoongbackend.domain.issue.issue.repository

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.projectyoongbackend.domain.issue.issue.dto.IssuePageResponse
import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import com.yoong.projectyoongbackend.domain.issue.issue.entity.QIssue
import com.yoong.projectyoongbackend.domain.issue.issue.repository.projectionDto.FindAllByTeamIdDto
import com.yoong.projectyoongbackend.domain.issue.issue.repository.projectionDto.QFindAllByTeamIdDto
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private


interface IssueRepository {

    fun save(issue: Issue): Issue

    fun findByIdOrNull(id: Long): Issue?

    fun findAllByTeamId(teamId: Long, pageable: Pageable): Page<FindAllByTeamIdDto>
}

interface IssueJpaRepository: JpaRepository<Issue, Long>

@Repository
class IssueRepositoryImpl(
    private val issueJpaRepository: IssueJpaRepository,
    @PersistenceContext
    private val em: EntityManager
): IssueRepository{

    private val queryFactory = JPAQueryFactory(em)
    private val issue = QIssue.issue

    override fun save(issue: Issue): Issue = issueJpaRepository.save(issue)

    override fun findByIdOrNull(id: Long): Issue?{

        return queryFactory.selectFrom(issue)
            .where(issue.id.eq(id))
            .where(issue.isDeleted.eq(false))
            .fetchOne()
    }

    override fun findAllByTeamId(teamId: Long, pageable: Pageable): Page<FindAllByTeamIdDto> {

        val query = queryFactory.select(
            QFindAllByTeamIdDto(
                issue.title,
                issue.important,
                issue.workingStatus,
                issue.createdAt,
                issue.updatedAt,
            )
            ).from(issue)
            .where(issue.teamId.eq(teamId))
            .where(issue.isDeleted.eq(false))
            .fetch()

        return PageImpl(query, pageable, query.size.toLong())
    }
}