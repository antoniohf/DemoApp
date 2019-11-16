package antoniohf.movierating.service

import antoniohf.movierating.dto.MovieDto
import antoniohf.movierating.mapper.MovieMapper
import antoniohf.movierating.repository.MovieRepository
import org.springframework.stereotype.Component

@Component
class MovieService (private val movieRepo: MovieRepository,
                    private val movieMapper: MovieMapper) {

    fun getAllMovies(): Set<MovieDto> = movieRepo.findAll().map {movieMapper.toMovieDto(it) }.toSet()

    fun getMovieByTitle(title: String): Set<MovieDto> = movieRepo.findByTitle(title).map {movieMapper.toMovieDto(it) }.toSet()

    fun addMovie(movieDto: MovieDto): Long = movieRepo.save(movieMapper.fromMovieDto(movieDto)).id!!

}

