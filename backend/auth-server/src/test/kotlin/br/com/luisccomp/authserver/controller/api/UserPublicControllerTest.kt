package br.com.luisccomp.authserver.controller.api

import br.com.luisccomp.authserver.domain.model.dto.response.user.UserResponse
import br.com.luisccomp.authserver.domain.repository.user.RoleRepository
import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import br.com.luisccomp.authserver.factory.RoleFactoryTest.adminRole
import br.com.luisccomp.authserver.factory.RoleFactoryTest.sellerRole
import br.com.luisccomp.authserver.factory.RoleFactoryTest.userRole
import br.com.luisccomp.authserver.factory.UserFactoryTest.anthonyUserPassword
import br.com.luisccomp.authserver.factory.UserFactoryTest.anthonyUserRequest
import br.com.luisccomp.authserver.factory.UserFactoryTest.anthonyUserUsername
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUser
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
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
internal class UserPublicControllerTest(
        @Autowired val mvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper
) {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @BeforeEach
    fun setUp() {
        roleRepository.saveAll(listOf(adminRole(), sellerRole(), userRole()))
        userRepository.save(claudiaUser())
    }

    @AfterEach
    fun tearDown() {
        roleRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `Should create a user on database`() {
        val result = mvc.perform(
                post("/public/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(anthonyUserRequest()))
        )
                .andExpect(status().isCreated)
                .andReturn()

        val response = objectMapper.readValue(result.response.contentAsString, UserResponse::class.java)

        assertEquals(anthonyUserUsername, response.username)

        val user = userRepository.findById(response.id).get()

        assertEquals(anthonyUserUsername, user.username)
        assertTrue(user.active)
        assertEquals(1, user.roles.size)
        assertNotEquals(anthonyUserPassword, user.password)
    }

    @Test
    fun `should throw an error when try to create a user with username already in use`() {
        val result = mvc.perform(
                post("/public/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claudiaUserRequest()))
        )
                .andExpect(status().isBadRequest)
                .andReturn()

        assertTrue(result.response.contentAsString.contains("Username already in use"))
    }
}