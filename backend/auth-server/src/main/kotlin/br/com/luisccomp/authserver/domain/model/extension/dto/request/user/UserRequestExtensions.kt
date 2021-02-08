package br.com.luisccomp.authserver.domain.model.extension.dto.request.user

import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.entity.user.User
import java.util.UUID

fun UserRequest.toEntity(id: UUID? = null) = User(
        id = id ?: UUID.randomUUID(),
        username = this.username,
        password = this.password
)