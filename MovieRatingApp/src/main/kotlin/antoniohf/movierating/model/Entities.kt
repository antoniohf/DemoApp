package antoniohf.movierating.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class IdentifiableEntity(@Id
                                  @SequenceGenerator(name="seqMovieId",sequenceName="seq_movie_id",allocationSize=10)
                                  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seqMovieId")
                                  var id: Long? = null)

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditableEntity(@CreatedBy
                               @OneToOne
                               @JoinColumn(name = "created_by")
                               var createdBy: User? = null,
                               @CreatedDate var creationDate: LocalDateTime? = null): IdentifiableEntity()

@Entity
data class Movie(
        var title: String,
        var releaseDate: LocalDate
        ): AuditableEntity()