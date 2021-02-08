package br.com.luisccomp.authserver.controller.api

import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserAuthenticationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/auth")
interface AuthenticationController {
    @PostMapping("/login")
    fun login(@RequestBody @Valid userRequest: UserRequest): ResponseEntity<UserAuthenticationResponse>
}