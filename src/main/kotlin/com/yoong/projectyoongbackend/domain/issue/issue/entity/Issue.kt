package com.yoong.projectyoongbackend.domain.issue.issue.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Issue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}