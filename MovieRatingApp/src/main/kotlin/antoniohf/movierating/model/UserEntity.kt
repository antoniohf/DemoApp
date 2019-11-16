package antoniohf.movierating.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.CascadeType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name="users")
data class User(var loginName: String,
                var password: String,
                var email: String,
                @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) var roles: Set<Role> = HashSet(),
                @Id
                @SequenceGenerator(name="seqUserId",sequenceName="seq_users_id",allocationSize=10)
                @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqUserId") var id: Long? = null): Serializable

@Entity
@Table(name="roles")
data class Role(var role: String = "",
                @Id
                @SequenceGenerator(name="seqRoleId",sequenceName="seq_roles_id",allocationSize=10)
                @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqRoleId") var id: Long? = null): Serializable