package br.com.luisccomp.authserver.service.user.impl

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.model.entity.user.Role
import br.com.luisccomp.authserver.domain.repository.user.RoleRepository
import br.com.luisccomp.authserver.exception.NotFoundException
import br.com.luisccomp.authserver.service.user.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {

    override fun getRoleByRole(role: RoleEnum): Role {
        return roleRepository.findByRole(role)
                .orElseThrow { NotFoundException("Role not found") }
    }

}