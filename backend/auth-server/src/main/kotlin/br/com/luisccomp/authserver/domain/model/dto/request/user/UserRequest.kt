package br.com.luisccomp.authserver.domain.model.dto.request.user

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty

data class UserRequest(
        @JsonProperty("username")
        @field:NotEmpty(message = "\"username\" is required and doesn't allow empty strings")
        @field:Length(max = 255, message = "\"username\" must contain up to 255 characters")
        val username: String,

        @JsonProperty("password")
        @field:NotEmpty(message = "\"password\" is required and doesn't allow empty strings")
        @field:Length(max = 255, message = "\"password\" must contain up to 255 characters")
        val password: String
)