package br.com.luisccomp.authserver.exception

import org.springframework.http.HttpStatus

open class BaseHttpException(
        message: String? = "Internal server error",
        val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        val details: Any? = null
) : RuntimeException(message)