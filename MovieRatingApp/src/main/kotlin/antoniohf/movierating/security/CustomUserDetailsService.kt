package antoniohf.movierating.security

import antoniohf.movierating.service.UserService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userService: UserService): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        username?: throw UsernameNotFoundException("Username not found!")
        val userDto = userService.findByLoginName(username)
        // Achtung bitte : this User object is from spring security!
        return User(userDto.loginName, userDto.password, userDto.roles.map { SimpleGrantedAuthority(it.role) })
    }
}