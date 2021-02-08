package br.com.luisccomp.authserver.domain.model.repository.user

import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUser
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserActive
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserId
import br.com.luisccomp.authserver.factory.UserFactoryTest.bobUserUsername
import br.com.luisccomp.authserver.factory.UserFactoryTest.claudiaUserUsername
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ActiveProfiles(value = ["test"])
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class UserRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

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
    fun `Return true when exists user with given username`() {
        assertTrue(userRepository.existsByUsername(bobUserUsername))
    }

    @Test
    fun `Return false when there is no user with given username`() {
        assertFalse(userRepository.existsByUsername(claudiaUserUsername))
    }

    @Test
    fun `Return a user when there is a user with given username`() {
        val user = userRepository.findByUsername(bobUserUsername)
                .get()

        assertEquals(bobUserId, user.id)
        assertEquals(bobUserUsername, user.username)
        assertEquals(bobUserActive, user.active)
    }

    @Test
    fun `Return an empty optional when there is no user with given username`() {
        val optional = userRepository.findByUsername(claudiaUserUsername)

        assertTrue(optional.isEmpty)
    }
}