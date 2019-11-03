package antoniohf.movierating.controller

import antoniohf.movierating.security.CustomUserDetailsService
import antoniohf.movierating.security.JwtTokenUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.Serializable


@RestController
@RequestMapping("/api/v1/authentication")
@CrossOrigin(origins = ["http://localhost:4200"])
class AuthenticationController(private val authenticationManager: AuthenticationManager,
                               private val userDetailsService: CustomUserDetailsService,
                               private val jwtTokenUtil: JwtTokenUtil) {

    @PostMapping("/")
    fun authenticateUser(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        try {
            authenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.loginName, authenticationRequest.password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.loginName)
        val token = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok<Any>(JwtResponse(token))
    }

}

data class JwtRequest(var loginName: String = "", var password: String = ""): Serializable

data class JwtResponse(var jwtToken: String = ""): Serializable

