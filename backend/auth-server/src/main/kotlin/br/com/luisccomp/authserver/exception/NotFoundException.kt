package br.com.luisccomp.authserver.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(
        message: String? = "Not found",
        details: Any? = null
) : BaseHttpException(message, HttpStatus.NOT_FOUND, details)