package com.yoong.projectyoongbackend.common.exception

import com.yoong.projectyoongbackend.common.exception.handler.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun modalNotFoundException(e: ModelNotFoundException): ResponseEntity<ModelNotFoundException>
        = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ModelNotFoundException(e.code, e.msg))

    @ExceptionHandler(DuplicatedException::class)
    fun duplicatedException(e: DuplicatedException): ResponseEntity<DuplicatedException>
            = ResponseEntity.status(HttpStatus.NOT_FOUND).body(DuplicatedException(e.code, e.msg))

    @ExceptionHandler(LoginFailedException::class)
    fun loginFailedException(e: LoginFailedException): ResponseEntity<LoginFailedException>
            = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginFailedException(e.msg))

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(e: BadRequestException): ResponseEntity<BadRequestException>
            = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BadRequestException(e.code, e.msg))

    @ExceptionHandler(AccessFailedException::class)
    fun accessFailedException(e: AccessFailedException): ResponseEntity<AccessFailedException>
            = ResponseEntity.status(HttpStatus.FORBIDDEN).body(AccessFailedException(e.code, e.msg))

}