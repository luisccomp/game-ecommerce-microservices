package br.com.luisccomp.authserver.domain.model.entity.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles
                .map { it.role }
                .toMutableList()
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return user.active
    }

    override fun isAccountNonLocked(): Boolean {
        return user.active
    }

    override fun isCredentialsNonExpired(): Boolean {
        return user.active
    }

    override fun isEnabled(): Boolean {
        return user.active
    }
}