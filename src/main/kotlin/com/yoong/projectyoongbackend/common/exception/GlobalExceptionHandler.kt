package com.yoong.projectyoongbackend.common.exception

import com.yoong.projectyoongbackend.common.dto.ErrorResponse
import com.yoong.projectyoongbackend.common.exception.handler.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun modalNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse>
        = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.code, e.msg))

    @ExceptionHandler(DuplicatedException::class)
    fun duplicatedException(e: DuplicatedException): ResponseEntity<ErrorResponse>
            = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.code, e.msg))

    @ExceptionHandler(LoginFailedException::class)
    fun loginFailedException(e: LoginFailedException): ResponseEntity<ErrorResponse>
            = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(401, e.msg))

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(e: BadRequestException): ResponseEntity<ErrorResponse>
            = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(e.code, e.msg))

    @ExceptionHandler(AccessFailedException::class)
    fun accessFailedException(e: AccessFailedException): ResponseEntity<ErrorResponse>
            = ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse(e.code, e.msg))

}