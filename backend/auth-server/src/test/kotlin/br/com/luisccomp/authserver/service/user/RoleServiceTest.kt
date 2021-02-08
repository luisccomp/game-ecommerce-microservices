package br.com.luisccomp.authserver.service.user

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.repository.user.RoleRepository
import br.com.luisccomp.authserver.exception.NotFoundException
import br.com.luisccomp.authserver.factory.RoleFactoryTest.userRole
import br.com.luisccomp.authserver.factory.RoleFactoryTest.userRoleId
import br.com.luisccomp.authserver.factory.RoleFactoryTest.userRoleRole
import br.com.luisccomp.authserver.service.user.impl.RoleServiceImpl
import br.com.luisccomp.authserver.testutils.any
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mockito.verify
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional

@ExtendWith(SpringExtension::class)
@ActiveProfiles(value = ["test"])
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class RoleServiceTest {
    @MockBean
    lateinit var roleRepository: RoleRepository

    lateinit var roleService: RoleService

    @BeforeEach
    fun setUp() {
        roleService = RoleServiceImpl(roleRepository)
    }

    @Test
    fun `should find a role given a role name`() {
        given(roleRepository.findByRole(any()))
                .willReturn(Optional.of(userRole()))

        val role = roleService.getRoleByRole(RoleEnum.USER)

        assertEquals(userRoleId, role.id)
        assertEquals(userRoleRole, role.role)

        verify(roleRepository, times(1)).findByRole(any())
    }

    @Test
    fun `should throw an error when there is no role with given name`() {
        given(roleRepository.findByRole(any()))
                .willReturn(Optional.empty())

        val exception = catchThrowable { roleService.getRoleByRole(RoleEnum.USER) }

        assertTrue(exception is NotFoundException)
        assertEquals("Role not found", exception.message)

        verify(roleRepository, times(1)).findByRole(any())
    }
}