package br.com.luisccomp.authserver.service.user.impl

import br.com.luisccomp.authserver.domain.model.extension.entity.user.toUserDetails
import br.com.luisccomp.authserver.domain.repository.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsServiceImpl")
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
                .orElseThrow { UsernameNotFoundException("User not found") }
                .toUserDetails()
    }
}