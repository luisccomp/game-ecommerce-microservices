package br.com.luisccomp.authserver.service.user.impl

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserResponse
import br.com.luisccomp.authserver.domain.model.entity.user.UserRole
import br.com.luisccomp.authserver.domain.model.extension.dto.request.user.toEntity
import br.com.luisccomp.authserver.domain.model.extension.entity.user.toResponse
import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import br.com.luisccomp.authserver.exception.BadRequestException
import br.com.luisccomp.authserver.exception.NotFoundException
import br.com.luisccomp.authserver.service.user.RoleService
import br.com.luisccomp.authserver.service.user.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val roleService: RoleService
) : UserService {
    override fun createUser(userRequest: UserRequest): UserResponse {
        if (userRepository.existsByUsername(userRequest.username))
            throw BadRequestException("Username already in use")

        val user = userRequest.toEntity()
        user.password = passwordEncoder.encode(user.password)

        val role = roleService.getRoleByRole(RoleEnum.USER)

        user.roles = mutableListOf(UserRole(user = user, role = role))

        return userRepository.save(user)
                .toResponse()
    }

    override fun findUserById(id: UUID): UserResponse {
        return userRepository.findById(id)
                .orElseThrow { NotFoundException("User not found") }
                .toResponse()
    }
}