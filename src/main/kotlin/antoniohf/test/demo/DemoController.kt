package antoniohf.test.demo

import antoniohf.test.demo.dto.StatusInfo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping("/status")
    fun getStatus(): StatusInfo = StatusInfo("Demo App running on Docker", "alpha v0.0.1")
}