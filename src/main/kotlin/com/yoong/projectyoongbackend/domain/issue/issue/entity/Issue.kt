package com.yoong.projectyoongbackend.domain.issue.issue.entity

import com.yoong.projectyoongbackend.domain.issue.issue.dto.RequestWorkingStatus
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.Important
import com.yoong.projectyoongbackend.domain.issue.issue.enumClass.WorkingStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
class Issue(

    @Column(name= "title", nullable = false)
    val title: String,

    @Column(name="description", nullable = false)
    val description: String,

    @Enumerated(EnumType.STRING)
    @Column(name="important", nullable = false)
    val important: Important,

    @Enumerated(EnumType.STRING)
    @Column(name="working_status", nullable = false)
    val workingStatus: WorkingStatus,

    @Column(name="is_deleted", nullable = false)
    val isDeleted: Boolean = false
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name="deleted_at", nullable = true)
    val deletedAt: LocalDateTime? = null
}