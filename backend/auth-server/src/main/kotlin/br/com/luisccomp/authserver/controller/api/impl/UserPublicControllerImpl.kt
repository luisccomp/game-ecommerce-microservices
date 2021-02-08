package br.com.luisccomp.authserver.controller.api.impl

import br.com.luisccomp.authserver.controller.api.UserPublicController
import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserResponse
import br.com.luisccomp.authserver.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UserPublicControllerImpl(
        private val userService: UserService
) : UserPublicController {
    override fun createUser(userRequest: UserRequest): ResponseEntity<UserResponse> {
        val user = userService.createUser(userRequest)

        val uri = URI.create("/users/${user.id}")

        return ResponseEntity.created(uri)
                .body(user)
    }
}