package br.com.luisccomp.authserver.service.user

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.model.entity.user.Role

interface RoleService {
    fun getRoleByRole(role: RoleEnum): Role
}