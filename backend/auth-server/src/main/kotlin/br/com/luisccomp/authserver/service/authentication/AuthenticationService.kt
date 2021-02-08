package br.com.luisccomp.authserver.service.authentication

import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserAuthenticationResponse

interface AuthenticationService {
    fun login(userRequest: UserRequest): UserAuthenticationResponse
}