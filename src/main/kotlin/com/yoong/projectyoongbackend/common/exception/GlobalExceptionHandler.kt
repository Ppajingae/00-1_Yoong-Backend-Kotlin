package com.yoong.projectyoongbackend.common.exception

import com.yoong.projectyoongbackend.common.exception.handler.BadRequestException
import com.yoong.projectyoongbackend.common.exception.handler.DuplicatedException
import com.yoong.projectyoongbackend.common.exception.handler.LoginFailedException
import com.yoong.projectyoongbackend.common.exception.handler.ModalNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModalNotFoundException::class)
    fun modalNotFoundException(e: ModalNotFoundException): ResponseEntity<ModalNotFoundException>
        = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ModalNotFoundException(e.code, e.msg))

    @ExceptionHandler(DuplicatedException::class)
    fun duplicatedException(e: DuplicatedException): ResponseEntity<DuplicatedException>
            = ResponseEntity.status(HttpStatus.NOT_FOUND).body(DuplicatedException(e.code, e.msg))

    @ExceptionHandler(LoginFailedException::class)
    fun loginFailedException(e: LoginFailedException): ResponseEntity<LoginFailedException>
            = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginFailedException(e.msg))

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(e: BadRequestException): ResponseEntity<BadRequestException>
            = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BadRequestException(e.code, e.msg))

}