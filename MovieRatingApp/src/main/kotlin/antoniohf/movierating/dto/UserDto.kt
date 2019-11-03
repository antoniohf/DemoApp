package antoniohf.movierating.dto

import java.io.Serializable

data class UserDto(val id: Long?,
                  val loginName: String,
                  val password: String,
                  val email: String,
                  val roles: Set<RoleDto> = HashSet()): Serializable

data class RoleDto(val role: String = ""): Serializable
