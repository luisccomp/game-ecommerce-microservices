package br.com.luisccomp.authserver.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(
        message: String? = "Bad request",
        details: Any? = null
) : BaseHttpException(message, HttpStatus.BAD_REQUEST, details)