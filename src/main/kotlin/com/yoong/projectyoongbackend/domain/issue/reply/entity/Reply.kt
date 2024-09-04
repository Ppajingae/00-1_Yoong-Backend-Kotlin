package com.yoong.projectyoongbackend.domain.issue.reply.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}