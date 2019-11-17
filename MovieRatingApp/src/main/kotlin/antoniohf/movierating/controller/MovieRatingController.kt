package antoniohf.movierating.controller

import antoniohf.movierating.dto.MovieDto
import antoniohf.movierating.dto.StatusInfo
import antoniohf.movierating.service.MovieService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/movies")
@Api(tags = ["Movie rating API"])
//@CrossOrigin(origins = ["http://localhost:4200"])
class MovieRatingController(private val movieService: MovieService) {

    @GetMapping("/")
    fun getMovieList(): ResponseEntity<*> = ResponseEntity.ok<Any>(movieService.getAllMovies())

    @GetMapping("/{title}")
    fun getMovieByTitle(@PathVariable title: String): ResponseEntity<*> = ResponseEntity.ok<Any>(movieService.getMovieByTitle(title))

    @PostMapping("/")
    fun addMovie(@RequestBody movieDto: MovieDto): ResponseEntity<*> = ResponseEntity.ok<Any>(movieService.addMovie(movieDto)) //TODO: this might need to change

    @GetMapping("/status")
    fun getStatus(): ResponseEntity<*> = ResponseEntity.ok<Any>(StatusInfo("Movie Rating App running on Docker", "alpha v0.0.1"))
}