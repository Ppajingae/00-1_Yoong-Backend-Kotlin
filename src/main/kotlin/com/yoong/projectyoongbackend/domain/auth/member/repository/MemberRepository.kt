package com.yoong.projectyoongbackend.domain.auth.member.repository

import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface MemberRepository {

    fun save(member: Member): Member

    fun findByUserId(userId: String): Member?
}

interface MemberJpaRepository : JpaRepository<Member, Long>{

    fun findByUserId(userId: String): Member?
}

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
): MemberRepository{

    override fun save(member: Member): Member = memberJpaRepository.save(member)

    override fun findByUserId(userId: String): Member? = memberJpaRepository.findByUserId(userId)
}