package br.com.luisccomp.authserver.exception.extension

import br.com.luisccomp.authserver.domain.model.dto.response.error.ErrorResponse
import br.com.luisccomp.authserver.exception.BaseHttpException

fun BaseHttpException.toErrorResponse() = ErrorResponse(
        status = this.status.value(),
        message = this.message,
        details = this.details
)