package br.com.luisccomp.authserver.factory

import br.com.luisccomp.authserver.domain.model.dto.request.user.UserRequest
import br.com.luisccomp.authserver.domain.model.entity.user.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.UUID

object UserFactoryTest {
    val bobUserId = UUID.fromString("3e932acd-2f15-4b98-a4f3-b368a36584e1")
    val bobUserUsername = "bob"
    val bobUserPassword = "bobSecretPassword@2020#"
    val bobUserActive = true

    fun bobUser() = User(
            id = bobUserId,
            username = bobUserUsername,
            password = BCryptPasswordEncoder().encode(bobUserPassword),
            active = bobUserActive
    )

    fun bobUserRequest() = UserRequest(
            username = bobUserUsername,
            password = bobUserPassword
    )

    val claudiaUserId = UUID.fromString("930f8673-285c-476a-af05-bd98bc1e52a4")
    val claudiaUserUsername = "claudia"
    val claudiaUserPassword = "claudiaSecretPassword@2020#"
    val claudiaUserActive = true

    fun claudiaUser() = User(
            id = claudiaUserId,
            username = claudiaUserUsername,
            password = claudiaUserPassword,
            active = claudiaUserActive
    )

    fun claudiaUserRequest() = UserRequest(
            username = claudiaUserUsername,
            password = claudiaUserPassword
    )

    val anthonyUserUsername = "anthony"
    val anthonyUserPassword = "anthonySecretPassword@2020"

    fun anthonyUserRequest() = UserRequest(
            username = anthonyUserUsername,
            password = anthonyUserPassword
    )
}