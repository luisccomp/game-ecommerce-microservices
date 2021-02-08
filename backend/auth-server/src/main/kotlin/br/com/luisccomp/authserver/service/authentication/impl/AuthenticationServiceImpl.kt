package br.com.luisccomp.authserver.service.authentication.impl

import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.dto.response.user.UserAuthenticationResponse
import br.com.luisccomp.authserver.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.authserver.exception.UnauthorizedException
import br.com.luisccomp.authserver.infrastructure.component.JwtTokenUtil
import br.com.luisccomp.authserver.service.authentication.AuthenticationService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        @Qualifier("userDetailsServiceImpl") private val userDetailsService: UserDetailsService,
        private val jwtTokenUtil: JwtTokenUtil,
        private val authenticationManager: AuthenticationManager
) : AuthenticationService {
    override fun login(userRequest: UserRequest): UserAuthenticationResponse {
        authenticate(userRequest.username, userRequest.password)

        val userDetails = userDetailsService.loadUserByUsername(userRequest.username) as UserDetailsImpl

        val roles = userDetails.authorities
                .map { it.authority }

        val claims = mutableMapOf(
                Pair("roles", roles as Any),
                Pair("id", userDetails.user.id)
        )

        return UserAuthenticationResponse(
                id = userDetails.user.id,
                username = userDetails.username,
                token = jwtTokenUtil.generateToken(userDetails, claims)
        )
    }

    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw UnauthorizedException("User disabled")
        } catch (e: BadCredentialsException) {
            throw UnauthorizedException("Invalid username or password")
        }
    }
}