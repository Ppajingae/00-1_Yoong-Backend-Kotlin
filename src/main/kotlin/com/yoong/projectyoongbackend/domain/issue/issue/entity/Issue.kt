package com.yoong.projectyoongbackend.domain.issue.issue.entity

import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "issue")
class Issue(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "important", nullable = false)
    var important: Important,

    @Enumerated(EnumType.STRING)
    @Column(name = "working_status", nullable = false)
    var workingStatus: WorkingStatus,

    @Column(name = "author", nullable = false)
    var author: String,

    @Column(name = "team_id", nullable = false)
    val teamId: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false

}