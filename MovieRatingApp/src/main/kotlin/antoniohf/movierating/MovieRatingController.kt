package antoniohf.movierating

import antoniohf.movierating.dto.StatusInfo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieRatingController {

    @GetMapping("/status")
    fun getStatus(): StatusInfo = StatusInfo("Movie Rating App running on Docker", "alpha v0.0.1")
}