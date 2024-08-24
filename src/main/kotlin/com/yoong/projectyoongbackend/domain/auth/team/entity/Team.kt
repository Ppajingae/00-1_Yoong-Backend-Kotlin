package com.yoong.projectyoongbackend.domain.auth.team.entity

import jakarta.persistence.*

@Entity
@Table(name = "team")
class Team(

    @Column(name = "name", nullable = false)
    val name: String,
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}