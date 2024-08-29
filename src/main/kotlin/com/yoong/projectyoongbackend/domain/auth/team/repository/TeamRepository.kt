package com.yoong.projectyoongbackend.domain.auth.team.repository

import com.yoong.projectyoongbackend.domain.auth.team.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface TeamRepository {
    fun findByDummyTeam(): Team

    fun save(team: Team) : Team

    fun findByIdOrNull(teamId: Long) : Team?

    fun delete(team: Team)

    fun saveAndFlush(team: Team): Team
}

interface TeamJpaRepository : JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t WHERE t.name = 'DUMMY_TEAM'")
    fun findByDummyTeam(): Team
}

@Repository
class TeamRepositoryImpl(
    private val teamJpaRepository: TeamJpaRepository
): TeamRepository {

    override fun findByDummyTeam(): Team = teamJpaRepository.findByDummyTeam()

    override fun save(team: Team): Team = teamJpaRepository.save(team)

    override fun findByIdOrNull(teamId: Long): Team? = teamJpaRepository.findByIdOrNull(teamId)

    override fun delete(team: Team) = teamJpaRepository.delete(team)

    override fun saveAndFlush(team: Team): Team = teamJpaRepository.saveAndFlush(team)
}