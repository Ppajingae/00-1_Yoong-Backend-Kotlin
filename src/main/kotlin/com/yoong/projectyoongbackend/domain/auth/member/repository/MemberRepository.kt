package com.yoong.projectyoongbackend.domain.auth.member.repository

import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

interface MemberRepository {

    fun save(member: Member): Member

    fun findByIdOrNull(id: Long): Member?
}

interface MemberJpaRepository : JpaRepository<Member, Long>

class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
): MemberRepository{

    override fun save(member: Member): Member = memberJpaRepository.save(member)

    override fun findByIdOrNull(id: Long): Member? = memberJpaRepository.findByIdOrNull(id)
}