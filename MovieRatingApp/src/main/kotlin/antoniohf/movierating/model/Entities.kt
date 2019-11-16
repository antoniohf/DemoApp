package antoniohf.movierating.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class Movie(
        var title: String,
        var releaseDate: LocalDate,
        @Id
        @SequenceGenerator(name="seqMovieId",sequenceName="seq_movie_id",allocationSize=10)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMovieId")
        var id: Long? = null): Serializable