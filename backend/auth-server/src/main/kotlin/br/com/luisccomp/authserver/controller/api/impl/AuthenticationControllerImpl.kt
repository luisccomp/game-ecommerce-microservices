package br.com.luisccomp.authserver.controller.api.impl

import br.com.luisccomp.authserver.controller.api.AuthenticationController
import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserAuthenticationResponse
import br.com.luisccomp.authserver.service.authentication.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationControllerImpl(
        private val authenticationService: AuthenticationService
) : AuthenticationController {

    override fun login(userRequest: UserRequest): ResponseEntity<UserAuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.login(userRequest))
    }

}