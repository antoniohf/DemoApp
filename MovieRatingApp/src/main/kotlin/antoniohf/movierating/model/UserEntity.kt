package antoniohf.movierating.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="users")
data class User(var loginName: String,
                var password: String,
                var email: String,
                @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) var roles: Set<Role> = HashSet(),
                @Id @GeneratedValue var id: Long? = null): Serializable

@Entity
data class Role(var role: String = "",
                @Id @GeneratedValue var id: Long? = null): Serializable