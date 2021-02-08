package br.com.luisccomp.authserver.service.user

import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import br.com.luisccomp.authserver.exception.BadRequestException
import br.com.luisccomp.authserver.exception.NotFoundException
import br.com.luisccomp.authserver.factory.RoleFactoryTest.userRole
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUser
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUserId
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUserRequest
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUserUsername
import br.com.luisccomp.authserver.service.user.impl.UserServiceImpl
import br.com.luisccomp.authserver.testutils.any
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.times
import org.mockito.Mockito.verify
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional

@ExtendWith(SpringExtension::class)
@ActiveProfiles(value = ["test"])
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserServiceTest {
    @MockBean
    lateinit var userRepository: UserRepository

    @MockBean
    lateinit var roleService: RoleService

    lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserServiceImpl(userRepository, BCryptPasswordEncoder(), roleService)

        given(roleService.getRoleByRole(any()))
                .willReturn(userRole())
    }

    @Test
    fun `should create a user`() {
        given(userRepository.save(any()))
                .willReturn(claudiaUser())

        val user = userService.createUser(claudiaUserRequest())

        assertEquals(claudiaUserId, user.id)
        assertEquals(claudiaUserUsername, user.username)

        verify(userRepository, times(1)).existsByUsername(any())
        verify(roleService, times(1)).getRoleByRole(any())
        verify(userRepository, times(1)).save(any())
    }

    @Test
    fun `should throw an error when username is already in use`() {
        given(userRepository.existsByUsername(any()))
                .willReturn(true)

        val exception = catchThrowable { userService.createUser(claudiaUserRequest()) }

        assertTrue(exception is BadRequestException)
        assertEquals("Username already in use", exception.message)

        verify(userRepository, times(1)).existsByUsername(any())
        verify(roleService, never()).getRoleByRole(any())
        verify(userRepository, never()).save(any())
    }

    @Test
    fun `should return a user when exists by id`() {
        given(userRepository.findById(any()))
                .willReturn(Optional.of(claudiaUser()))

        val user = userService.findUserById(claudiaUserId)

        assertEquals(claudiaUserId, user.id)
        assertEquals(claudiaUserUsername, user.username)

        verify(userRepository, times(1)).findById(any())
    }

    @Test
    fun `should throw an error when there is no user with given id`() {
        given(userRepository.findById(any()))
                .willReturn(Optional.empty())

        val exception = catchThrowable { userService.findUserById(claudiaUserId) }

        assertTrue(exception is NotFoundException)
        assertEquals("User not found", exception.message)

        verify(userRepository, times(1)).findById(any())
    }
}