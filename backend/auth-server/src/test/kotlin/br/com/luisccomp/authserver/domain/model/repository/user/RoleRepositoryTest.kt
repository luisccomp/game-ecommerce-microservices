package br.com.luisccomp.authserver.domain.model.repository.user

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.repository.user.RoleRepository
import br.com.luisccomp.authserver.factory.RoleFactoryTest
import org.junit.jupiter.api.AfterEach
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
class RoleRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var roleRepository: RoleRepository

    @BeforeEach
    fun setUp() {
        roleRepository.saveAll(
                listOf(RoleFactoryTest.adminRole(), RoleFactoryTest.sellerRole(), RoleFactoryTest.userRole())
        )
    }

    @AfterEach
    fun tearDown() {
        roleRepository.deleteAll()
    }

    @Test
    fun `should return a role when exists a role with given name`() {
        val optional = roleRepository.findByRole(RoleEnum.USER)

        assertTrue(optional.isPresent)
    }
}