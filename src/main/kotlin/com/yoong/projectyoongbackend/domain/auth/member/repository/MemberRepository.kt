package com.yoong.projectyoongbackend.domain.auth.member.repository

import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository {
    fun save(member: Member): Member
}

interface MemberJpaRepository : JpaRepository<Member, Long>

class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
): MemberRepository{

    override fun save(member: Member): Member {
        return memberJpaRepository.save(member)
    }
}