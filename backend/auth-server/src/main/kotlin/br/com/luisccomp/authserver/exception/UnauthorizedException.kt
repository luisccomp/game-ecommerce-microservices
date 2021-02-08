package br.com.luisccomp.authserver.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(
        message: String? = "Unauthorized",
        details: Any? = null
) : BaseHttpException(message, HttpStatus.UNAUTHORIZED, details)