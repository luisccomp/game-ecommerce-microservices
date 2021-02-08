package br.com.luisccomp.authserver.domain.model.dto.response.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.UUID

@JsonPropertyOrder(value = ["id", "username"])
data class UserResponse(
        @JsonProperty("id")
        val id: UUID,

        @JsonProperty("username")
        val username: String
)