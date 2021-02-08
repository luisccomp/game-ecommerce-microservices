package br.com.luisccomp.authserver.domain.repository.user

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.model.entity.user.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface RoleRepository : JpaRepository<Role, UUID> {
    fun findByRole(role: RoleEnum): Optional<Role>
}