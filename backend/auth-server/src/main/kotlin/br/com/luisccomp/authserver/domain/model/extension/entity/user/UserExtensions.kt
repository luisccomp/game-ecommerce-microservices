package br.com.luisccomp.authserver.domain.model.extension.entity.user

import br.com.luisccomp.authserver.domain.model.dto.response.user.UserResponse
import br.com.luisccomp.authserver.domain.model.entity.user.User
import br.com.luisccomp.authserver.domain.model.entity.user.UserDetailsImpl

fun User.toUserDetails() = UserDetailsImpl(user = this)

fun User.toResponse() = UserResponse(
        id = this.id,
        username = this.username
)