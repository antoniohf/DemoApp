package antoniohf.movierating.security

import org.springframework.stereotype.Component
import java.io.Serializable
import org.springframework.security.core.userdetails.UserDetails
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Claims
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.*

@Component
class JwtTokenUtil: Serializable {

    private val jwtTokenValidity = (5 * 60 * 60).toLong()

    @Value("\${jwt.secret}")
    private val secret: String? = null

    //retrieve username from jwt token
    fun getUsernameFromToken(token: String): String {
        return getAllClaimsFromToken(token).subject
    }

    //retrieve expiration date from jwt token
    private fun getExpirationDateFromToken(token: String): Date {
        return getAllClaimsFromToken(token).expiration
    }

    //for retrieving any information from token we will need the secret key
    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    //check if the token has expired
    private fun isTokenExpired(token: String): Boolean {
        return getExpirationDateFromToken(token).before(Date())
    }

    //generate token for user
    fun generateToken(userDetails: UserDetails): String {
        return doGenerateToken(HashMap(), userDetails.username)
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    //validate token
    fun validateToken(token: String?, userDetails: UserDetails): Boolean {
        return token != null && getUsernameFromToken(token) == userDetails.username && !isTokenExpired(token)
    }
}