package br.com.luisccomp.authserver.domain.repository.user

import br.com.luisccomp.authserver.domain.model.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsername(username: String): Optional<User>

    fun existsByUsername(username: String): Boolean
}