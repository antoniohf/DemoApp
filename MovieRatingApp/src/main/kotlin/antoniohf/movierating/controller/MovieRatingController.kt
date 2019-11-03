package antoniohf.movierating.controller

import antoniohf.movierating.dto.MovieDto
import antoniohf.movierating.dto.StatusInfo
import antoniohf.movierating.service.MovieService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = ["http://localhost:4200"])
class MovieRatingController(private val movieService: MovieService) {

    @GetMapping("/")
    fun getMovieList(): Set<MovieDto> = movieService.getAllMovies()

    @GetMapping("/{title}")
    fun getMovieByTitle(@PathVariable title: String): Set<MovieDto> = movieService.getMovieByTitle(title)

    @PostMapping("/")
    fun addMovie(): Long = movieService.addMovie(MovieDto(title = "test", releaseDate = LocalDate.now()))
//    fun addMovie(@RequestBody movieDto: MovieDto): Long = movieService.addMovie(movieDto)

    @GetMapping("/status")
    fun getStatus(): StatusInfo = StatusInfo("Movie Rating App running on Docker", "alpha v0.0.1")
}