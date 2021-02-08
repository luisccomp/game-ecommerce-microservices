package br.com.luisccomp.authserver.infrastructure.component

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenUtil {
    companion object {
        const val JWT_TOKEN_VALIDITY = 18_000_000L
    }

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun getAllClaimsFromToken(token: String): Claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body

    fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T = getAllClaimsFromToken(token)
            .let(claimsResolver)

    fun getUsernameFromToken(token: String): String? = getClaimFromToken(token, Claims::getSubject)

    fun getExpirationDateFromToken(token: String): Date = getClaimFromToken(token, Claims::getExpiration)

    fun isValidToken(token: String, userDetails: UserDetails): Boolean =
            getUsernameFromToken(token) == userDetails.username && !isTokenExpired(token)

    fun generateToken(userDetails: UserDetails, claims: MutableMap<String, Any> = mutableMapOf()): String =
            doGenerateToken(userDetails.username, claims)

    private fun doGenerateToken(subject: String, claims: MutableMap<String, Any>): String {
        val currentDateInMillis = System.currentTimeMillis()

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date(currentDateInMillis))
                .setExpiration(Date(currentDateInMillis + JWT_TOKEN_VALIDITY * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    private fun isTokenExpired(token: String): Boolean = getExpirationDateFromToken(token)
            .before(Date(System.currentTimeMillis()))
}