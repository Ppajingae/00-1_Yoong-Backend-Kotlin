package com.yoong.projectyoongbackend.domain.auth.member.entity

import com.yoong.projectyoongbackend.domain.auth.member.dto.ValidType
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role
import com.yoong.projectyoongbackend.domain.auth.team.entity.Team
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member(

    @Column(name = "user_id", nullable = false)
    var userId : String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "provider_email", nullable = true)
    val providerEmail: String? = null,

    @Column(name = "provider_id", nullable = true)
    val providerId: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: Role,

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    var position: Position,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    var team: Team?,
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    @Column(name = "is_id", nullable = false)
    var isId: Boolean = false

    @Column(name = "is_nickname", nullable = false)
    var isNickName: Boolean = false

    @Column(name = "is_email", nullable = false)
    var isEmail: Boolean = false

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    fun updateValid(validType: ValidType, argument: String){
        when(validType){
            ValidType.USER_ID ->{
                this.userId = argument
                this.isId = true
            }
            ValidType.EMAIL -> {
                this.email = argument
                this.isEmail = true
            }
            ValidType.NICKNAME -> {
                this.nickname = argument
                this.isNickName = true
            }
        }
    }

    fun updateProfile(password: String, team: Team){
        this.team = team
        this.password = password
    }

    fun setRoleAndPosition(setRole: Role, setPosition: Position) {
        this.role = setRole
        this.position = setPosition
    }

    fun setManager() {
        this.position = Position.MANAGER
    }

    fun setMember(setTeam: Team, setRole: Role, setPosition: Position) {
        this.role = setRole
        this.team = setTeam
        this.position = setPosition
    }

    fun approveTeam(setTeam: Team) {
        this.team = setTeam
    }

}