package antoniohf.movierating.controller

import antoniohf.movierating.security.CustomUserDetailsService
import antoniohf.movierating.security.JwtTokenUtil
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.Serializable


@RestController
@RequestMapping("/api/v1")
@Api(tags = ["Authentication API"])
@CrossOrigin(origins = ["http://localhost:4200"])
class AuthenticationController(private val authenticationManager: AuthenticationManager,
                               private val userDetailsService: CustomUserDetailsService,
                               private val jwtTokenUtil: JwtTokenUtil) {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @PostMapping("/authenticate")
    fun authenticateUser(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
       logger.info("authenticating user \"${authenticationRequest.username}\"")
        try {
            authenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password))
        } catch (e: DisabledException) {
            logger.error("user account is disabled $e")
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            logger.error("invalid credentials $e")
            throw Exception("INVALID_CREDENTIALS", e)
        }
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok<Any>(JwtResponse(token))
    }

}

data class JwtRequest(var username: String = "", var password: String = ""): Serializable

data class JwtResponse(var jwtToken: String = ""): Serializable

