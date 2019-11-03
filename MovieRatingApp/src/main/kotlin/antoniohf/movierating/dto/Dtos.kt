package antoniohf.movierating.dto

import java.io.Serializable
import java.time.LocalDate

data class MovieDto(val title: String,
                    val releaseDate: LocalDate,
                    val id: Long? = null): Serializable