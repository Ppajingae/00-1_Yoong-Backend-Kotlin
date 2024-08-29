package com.yoong.projectyoongbackend.domain.auth.team.entity

import com.yoong.projectyoongbackend.domain.auth.team.dto.TeamRequest
import jakarta.persistence.*

@Entity
@Table(name = "team")
class Team(

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name="people_count", nullable = false)
    var peopleCount: Int = 1
){


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun setCount(count: Int) {
        this.peopleCount += count
    }

    fun update(teamRequest: TeamRequest) {
        this.name = teamRequest.name
    }
}