package antoniohf.movierating.service

import antoniohf.movierating.dto.UserDto
import antoniohf.movierating.mapper.UserMapper
import antoniohf.movierating.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserService(private val userRepository: UserRepository,
                  private val userMapper: UserMapper) {

    fun findByLoginName(loginName: String): UserDto = userMapper.toUserDto(
            userRepository.findByLoginName(loginName).orElseThrow { UsernameNotFoundException("Username not found!") } )
}