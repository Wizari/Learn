package com.gmail.wizaripost.learning.feignClientTest

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "randomService",
    url = "http://localhost:30103/api/logic/slots/rng",
    configuration = [FeignConfig::class]
    )
interface RNGClient {
    @PostMapping("/seed")
    fun getSeed(): String
}


