package antoniohf.movierating.repository

import antoniohf.movierating.model.Movie
import org.springframework.data.repository.CrudRepository

interface MovieRepository: CrudRepository<Movie, Long> {
    fun findByTitle(title: String): List<Movie>
}