package com.yoong.projectyoongbackend.domain.auth.member.repository

import com.yoong.projectyoongbackend.domain.auth.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface MemberRepository {

    fun save(member: Member): Member

    fun findByUserId(userId: String): Member?

    fun existsByUserId(userId: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun saveAndFlush(member: Member): Member

    fun findByIdOrNull(validId: Long): Member?

}

interface MemberJpaRepository : JpaRepository<Member, Long>{

    fun findByUserId(userId: String): Member?

    fun existsByUserId(userId: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(email: String): Boolean

}

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository
): MemberRepository{

    override fun save(member: Member): Member = memberJpaRepository.save(member)

    override fun findByUserId(userId: String): Member? = memberJpaRepository.findByUserId(userId)

    override fun existsByUserId(userId: String): Boolean = memberJpaRepository.existsByUserId(userId)

    override fun existsByNickname(nickname: String): Boolean = memberJpaRepository.existsByNickname(nickname)

    override fun existsByEmail(email: String): Boolean = memberJpaRepository.existsByEmail(email)

    override fun saveAndFlush(member: Member): Member = memberJpaRepository.saveAndFlush(member)

    override fun findByIdOrNull(validId: Long): Member? = memberJpaRepository.findByIdOrNull(validId)
}