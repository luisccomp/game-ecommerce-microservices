package br.com.luisccomp.authserver.domain.model.entity.user

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
        @Id
        @Column(name = "id", nullable = false, unique = true)
        var id: UUID = UUID.randomUUID(),

        @Column(name = "username", nullable = false, unique = true)
        var username: String,

        @Column(name = "password", nullable = false)
        var password: String,

        @Column(name = "active", nullable = false)
        var active: Boolean = true,

        @CreationTimestamp
        @Column(name = "created_at", nullable = false)
        var createdAt: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),

        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var roles: MutableList<UserRole> = mutableListOf()
)