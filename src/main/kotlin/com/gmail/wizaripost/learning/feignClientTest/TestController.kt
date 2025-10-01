package com.gmail.wizaripost.learning.feignClientTest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val dzenService: DzenService
) {
    @GetMapping
    fun test(): String = dzenService.fetchPage()
}
