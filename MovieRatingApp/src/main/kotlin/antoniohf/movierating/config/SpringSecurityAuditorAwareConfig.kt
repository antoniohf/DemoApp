package antoniohf.movierating.config

import antoniohf.movierating.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.userdetails.User
import java.util.*
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@Configuration
@EnableJpaAuditing
internal class JpaAuditorAwareConfig {

    @Bean
    fun auditorProvider(userRepository: UserRepository): AuditorAware<antoniohf.movierating.model.User> {
        return JpaAuditorAware(userRepository)
    }
}

class JpaAuditorAware(private val userRepository: UserRepository) : AuditorAware<antoniohf.movierating.model.User> {

    override fun getCurrentAuditor(): Optional<antoniohf.movierating.model.User> {
        var user = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User::class.java::cast)
        if(user.isPresent) {
            return userRepository.findByLoginName(user.get().username)
        }
        else {
            return Optional.empty()
        }
    }
}