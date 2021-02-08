package br.com.luisccomp.authserver.controller.api

import br.com.luisccomp.authserver.domain.model.dto.response.user.UserAuthenticationResponse
import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUser
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserId
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserRequest
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserUsername
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = ["test"])
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@AutoConfigureMockMvc
class AuthenticationControllerTest(
        @Autowired val mvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper
) {
    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        userRepository.save(bobUser())
    }

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Test
    fun `should return a token when given valid user credentials`() {
        val result = mvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bobUserRequest()))
        )
                .andExpect(status().isOk)
                .andReturn()

        val response = objectMapper.readValue(result.response.contentAsString, UserAuthenticationResponse::class.java)

        assertEquals(bobUserId, response.id)
        assertEquals(bobUserUsername, response.username)
    }
}