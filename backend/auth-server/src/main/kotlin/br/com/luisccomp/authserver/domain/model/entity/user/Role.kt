package br.com.luisccomp.authserver.domain.model.entity.user

import br.com.luisccomp.authserver.domain.enums.RoleEnum
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "roles")
class Role(
        @Id
        @Column(name = "id", nullable = false, unique = true)
        var id: UUID = UUID.randomUUID(),

        @Column(name = "role", nullable = false)
        @Enumerated(EnumType.STRING)
        var role: RoleEnum,

        @CreationTimestamp
        @Column(name = "created_at", nullable = false)
        var createdAt: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),

        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "role_id")
        var users: MutableList<UserRole> = mutableListOf()
) : GrantedAuthority {
    override fun getAuthority(): String {
        return role.name
    }
}