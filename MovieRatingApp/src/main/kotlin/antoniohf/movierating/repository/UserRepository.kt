package antoniohf.movierating.repository

import antoniohf.movierating.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {

    fun findByLoginName(loginName: String): User?
}