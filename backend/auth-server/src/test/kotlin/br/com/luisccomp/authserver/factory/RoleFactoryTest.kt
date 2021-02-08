package br.com.luisccomp.authserver.factory

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.model.entity.user.Role
import java.util.UUID

object RoleFactoryTest {
    val adminRoleId = UUID.fromString("8a18bd63-ca30-40cc-b3df-d23cd0f9ee0e")
    val adminRoleRole = RoleEnum.ADMIN

    fun adminRole() = Role(
            id = adminRoleId,
            role = adminRoleRole
    )

    val sellerRoleId = UUID.fromString("4320b065-1ded-4bf5-b9f3-41112284c013")
    val sellerRoleRole = RoleEnum.SELLER

    fun sellerRole() = Role(
            id = sellerRoleId,
            role = sellerRoleRole
    )

    val userRoleId = UUID.fromString("bf7fd2a5-f27b-40db-9468-0f316dc0138e")
    val userRoleRole = RoleEnum.USER

    fun userRole() = Role(
            id = userRoleId,
            role = userRoleRole
    )
}