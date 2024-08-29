package com.yoong.projectyoongbackend.domain.auth.team.controller

import com.yoong.projectyoongbackend.common.dto.DefaultResponse
import com.yoong.projectyoongbackend.domain.auth.member.enumClass.Position
import com.yoong.projectyoongbackend.domain.auth.team.dto.TeamRequest
import com.yoong.projectyoongbackend.domain.auth.team.service.TeamService
import com.yoong.projectyoongbackend.infra.jwt.UserPrincipal
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/team")
class TeamController(
    private val teamService: TeamService
){

    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping
    fun createTeam(
        @RequestBody teamRequest: TeamRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ):ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(teamService.createTeam(teamRequest, userPrincipal.id))

    @PreAuthorize("@userSecurityService.hasLeaderPosition(principal)")
    @PutMapping("/{teamId}")
    fun updateTeam(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable teamId: Long,
        @RequestBody teamRequest: TeamRequest,
    ):ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(teamService.updateTeam(teamRequest, teamId))

    @PreAuthorize("@userSecurityService.hasLeaderPosition(principal)")
    @DeleteMapping("/{teamId}")
    fun deleteTeam(
        @PathVariable("teamId") teamId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ):ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(teamService.deleteTeam(teamId, userPrincipal.id))

    @PreAuthorize("@userSecurityService.hasLeaderPosition(principal)")
    @PatchMapping("/{teamId}/manager/{userId}")
    fun grantManagerPrivileges(
        @PathVariable teamId: Long,
        @PathVariable userId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ):ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(teamService.grantManagerPrivileges(userId, teamId))

    @PreAuthorize("@userSecurityService.hasLeaderPosition(principal)")
    @PatchMapping("/{teamId}/invite/{userId}")
    fun inviteTeam(
        @PathVariable teamId: Long,
        @PathVariable userId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ):ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(teamService.inviteTeam(teamId, userId))

    @PreAuthorize("@userSecurityService.hasLeaderPosition(principal)")
    @PatchMapping("/{teamId}/fire/{userId}")
    fun firedTeam(
        @PathVariable teamId: Long,
        @PathVariable userId: Long,
    ):ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(teamService.firedTeam(teamId, userId))

}