package com.yoong.projectyoongbackend.domain.issue.reply.entity

import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import com.yoong.projectyoongbackend.domain.issue.issue.entity.Issue
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.context.annotation.Description
import java.time.Instant
import java.time.LocalDateTime

@SQLRestriction("is_deleted=false")
@Entity
class Reply(

    @Column(name="description", nullable = false)
    var description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="issue_id", nullable = false)
    val issue: Issue,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    val member: Member
){

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name ="deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
}