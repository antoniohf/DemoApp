package antoniohf.movierating.repository

import antoniohf.movierating.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<User, Long> {

    fun findByLoginName(loginName: String): Optional<User>
}