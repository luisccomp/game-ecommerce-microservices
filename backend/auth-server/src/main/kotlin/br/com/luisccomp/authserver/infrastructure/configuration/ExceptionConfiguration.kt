package br.com.luisccomp.authserver.infrastructure.configuration

import br.com.luisccomp.authserver.exception.BaseHttpException
import br.com.luisccomp.authserver.exception.extension.toErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionConfiguration : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BaseHttpException::class)
    fun handleBaseHttpException(ex: BaseHttpException, request: WebRequest): ResponseEntity<Any> =
            handleExceptionInternal(ex, ex.toErrorResponse(), HttpHeaders(), ex.status, request)
}