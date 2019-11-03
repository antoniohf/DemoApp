package antoniohf.movierating.mapper

import antoniohf.movierating.dto.MovieDto
import antoniohf.movierating.model.Movie
import org.springframework.stereotype.Component

@Component
class MovieMapper() {
    fun toMovieDto(movie: Movie): MovieDto = MovieDto(id = movie.id, title = movie.title, releaseDate = movie.releaseDate)

    fun fromMovieDto(movieDto: MovieDto): Movie = Movie(title = movieDto.title, releaseDate = movieDto.releaseDate)
}