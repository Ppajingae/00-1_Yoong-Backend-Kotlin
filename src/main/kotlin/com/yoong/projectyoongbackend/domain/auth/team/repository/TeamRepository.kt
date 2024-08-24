package com.yoong.projectyoongbackend.domain.auth.team.repository

import com.yoong.projectyoongbackend.domain.auth.team.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

interface TeamRepository {
    fun findByDummyTeam(): Team
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
}