package antoniohf.movierating

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MovieRatingApplication

fun main(args: Array<String>) {
	runApplication<MovieRatingApplication>(*args)
}
