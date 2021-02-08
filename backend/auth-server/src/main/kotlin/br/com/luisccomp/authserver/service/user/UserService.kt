package br.com.luisccomp.authserver.service.user

import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserResponse
import java.util.UUID

interface UserService {
    fun createUser(userRequest: UserRequest): UserResponse

    fun findUserById(id: UUID): UserResponse
}