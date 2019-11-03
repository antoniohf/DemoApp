package antoniohf.movierating.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Movie(
        var title: String,
        var releaseDate: LocalDate,
        @Id @GeneratedValue var id: Long? = null): Serializable