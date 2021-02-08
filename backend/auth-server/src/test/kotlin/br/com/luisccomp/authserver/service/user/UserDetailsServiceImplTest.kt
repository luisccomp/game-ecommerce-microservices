package br.com.luisccomp.authserver.service.user

import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUser
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserUsername
import br.com.luisccomp.authserver.service.user.impl.UserDetailsServiceImpl
import br.com.luisccomp.authserver.testutils.any
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional

@ExtendWith(SpringExtension::class)
@ActiveProfiles(value = ["test"])
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserDetailsServiceImplTest {
    @MockBean
    lateinit var userRepository: UserRepository

    lateinit var userDetailsService: UserDetailsService

    @BeforeEach
    fun setUp() {
        userDetailsService = UserDetailsServiceImpl(userRepository)
    }

    @Test
    fun `should return user details when there is user with given username`() {
        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(bobUser()))

        val userDetails = userDetailsService.loadUserByUsername(bobUserUsername)

        assertEquals(bobUserUsername, userDetails.username)
    }

    @Test
    fun `should throw an error when there is no user with given username`() {
        given(userRepository.findByUsername(any()))
                .willReturn(Optional.empty())

        val exception = catchThrowable { userDetailsService.loadUserByUsername(bobUserUsername) }

        assertTrue(exception is UsernameNotFoundException)
        assertEquals("User not found", exception.message)
    }
}