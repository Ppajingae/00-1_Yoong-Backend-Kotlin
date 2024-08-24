package com.yoong.projectyoongbackend.domain.auth.member.entity

import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(

    @Column(name = "user_id", nullable = false)
    val userId : String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "nickName", nullable = false)
    val nickName: String,

    @Column(name = "provider_email", nullable = true)
    val providerEmail: String? = null,

    @Column(name = "provider_id", nullable = true)
    val providerId: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: Role,

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    val position: Position,

    @Column(name = "team_id", nullable = false)
    val teamId: Long? = null,
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null
}