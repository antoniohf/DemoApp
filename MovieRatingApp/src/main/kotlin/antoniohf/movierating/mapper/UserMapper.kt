package antoniohf.movierating.mapper

import antoniohf.movierating.dto.RoleDto
import antoniohf.movierating.dto.UserDto
import antoniohf.movierating.model.Role
import antoniohf.movierating.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toUserDto(user: User): UserDto = UserDto(id = user.id,
                                                loginName = user.loginName,
                                                email = user.email,
                                                password = user.password,
                                                roles = user.roles.map { RoleDto(it.role) }.toSet())

    fun fromUserDto(userDto: UserDto): User = User(id = userDto.id,
                                                    loginName = userDto.loginName,
                                                    email = userDto.email,
                                                    password = userDto.password,
                                                    roles = userDto.roles.map { Role(it.role) }.toSet())
}