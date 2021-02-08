package br.com.luisccomp.authserver.domain.model.dto.response.error

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.time.OffsetDateTime
import java.time.ZoneOffset

@JsonPropertyOrder(value = ["status", "message", "timestamp", "details"])
data class ErrorResponse(
        @JsonPropertyOrder("status")
        val status: Int,

        @JsonPropertyOrder("message")
        val message: String?,

        @JsonPropertyOrder("timestamp")
        val timestamp: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),

        @JsonPropertyOrder("details")
        val details: Any? = null
)
