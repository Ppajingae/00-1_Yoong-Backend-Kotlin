package com.yoong.projectyoongbackend.domain.auth.team.service

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.common.exception.handler.BadRequestException
import com.yoong.projectyoongbackend.common.exception.handler.ModalNotFoundException
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Role
import com.yoong.projectyoongbackend.domain.auth.member.repository.MemberRepository
import com.yoong.projectyoongbackend.domain.auth.team.dto.TeamRequest
import com.yoong.projectyoongbackend.domain.auth.team.entity.Team
import com.yoong.projectyoongbackend.domain.auth.team.repository.TeamRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository
){

    @Transactional
    fun createTeam(teamRequest: TeamRequest, memberId: Long): DefaultResponse {

        val member = memberRepository.findByIdOrNull(memberId)?: throw ModalNotFoundException(404, "맴버가 존재 하지 않습니다")

        if(member.team!!.name != "DUMMY_TEAM") throw BadRequestException(400, "팀에서 탈퇴 먼저 해주세요")

        val dummyTeam = teamRepository.findByDummyTeam()

        val team = teamRepository.saveAndFlush(
            Team(
                name = teamRequest.name,
            )
        )

        member.setRoleAndPosition(Role.ISSUE, Position.LEADER)

        member.approveTeam(team)

        memberRepository.save(member)

        dummyTeam.setCount(-1)

        teamRepository.save(dummyTeam)

        return DefaultResponse.from("팀 생성이 완료 되었습니다")
    }

    @Transactional
    fun updateTeam(teamRequest: TeamRequest, teamId: Long): DefaultResponse {

        if(teamId == 1L) throw BadRequestException(400, "임시 팀은 선택 할 수 없습니다")

        val team = teamRepository.findByIdOrNull(teamId) ?: throw ModalNotFoundException(404, "팀이 존재 하지 않습니다")

        team.update(teamRequest)

        teamRepository.save(team)

        return DefaultResponse.from("팀 명 변경이 완료 되었습니다")
    }

    @Transactional
    fun deleteTeam(teamId: Long, leaderId: Long): DefaultResponse {

        if(teamId == 1L) throw BadRequestException(400, "임시 팀은 선택 할 수 없습니다")

        val team = teamRepository.findByIdOrNull(teamId) ?: throw ModalNotFoundException(404, "팀이 존재 하지 않습니다")

        val dummyTeam = teamRepository.findByDummyTeam()

        val leader = memberRepository.findByIdOrNull(leaderId) ?: throw ModalNotFoundException(404, "리더가 존재 하지 않습니다")

        if(1 < team.peopleCount) throw BadRequestException(400, "팀 내 맴버가 남아 있습니다 확인 부탁 드립니다")

        leader.setRoleAndPosition(Role.MEMBER, Position.MEMBER)

        leader.approveTeam(dummyTeam)

        dummyTeam.setCount(1)

        memberRepository.saveAndFlush(leader)

        teamRepository.save(dummyTeam)

        teamRepository.delete(team)

        return DefaultResponse.from("팀 삭제가 완료 되었습니다")
    }

    @Transactional
    fun grantManagerPrivileges(userId: Long, teamId: Long): DefaultResponse {

        if(teamId == 1L) throw BadRequestException(400, "임시 팀은 선택 할 수 없습니다")

        val member = memberRepository.findByIdOrNull(userId) ?: throw ModalNotFoundException(404, "맴버가 존재 하지 않습니다")

        val team = teamRepository.findByIdOrNull(teamId) ?: throw ModalNotFoundException(404, "팀이 존재 하지 않습니다")

        if(member.team!!.id != teamId) throw BadRequestException(400, "다른 팀 맴버 입니다")

        if(member.position != Position.MEMBER) throw BadRequestException(400, "이미 다른 보직을 맡고 있습니다")

        member.setManager()

        memberRepository.save(member)

        return DefaultResponse.from("맴버 권한 수정이 완료 되었습니다")
    }

    @Transactional
    fun inviteTeam(teamId: Long, userId: Long): DefaultResponse {

        if(teamId == 1L) throw BadRequestException(400, "임시 팀은 선택 할 수 없습니다")

        val team = teamRepository.findByIdOrNull(teamId) ?: throw ModalNotFoundException(404, "팀이 존재 하지 않습니다")

        val member = memberRepository.findByIdOrNull(userId) ?: throw ModalNotFoundException(404, "맴버가 존재 하지 않습니다")

        if(member.team!!.id != 1L) throw BadRequestException(400, "다른 팀 소속 입니다")

        member.setMember(team, Role.ISSUE, Position.MEMBER)

        team.setCount(1)

        memberRepository.save(member)

        teamRepository.save(team)

        return DefaultResponse.from("팀에 추가 완료 되었습니다")

    }

    @Transactional
    fun firedTeam(teamId: Long, userId: Long): DefaultResponse {

        if(teamId == 1L) throw BadRequestException(400, "임시 팀은 선택 할 수 없습니다")

        val team = teamRepository.findByIdOrNull(teamId) ?: throw ModalNotFoundException(400, "팀이 존재 하지 않습니다")

        val dummyTeam = teamRepository.findByDummyTeam()

        val member = memberRepository.findByIdOrNull(userId) ?: throw ModalNotFoundException(400, "맴버가 존재 하지 않습니다")

        if(member.team!!.id != teamId) throw BadRequestException(400, "다른 팀 소속 입니다")

        member.setMember(dummyTeam, Role.MEMBER, Position.MEMBER)

        team.setCount(-1)

        memberRepository.save(member)

        teamRepository.save(team)

        return DefaultResponse.from("팀에 추가 완료 되었습니다")
    }


}