package com.gmail.wizaripost.learning.feignClientTest

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "dzenClient",
//    url = "https://dzen.ru",
    url = "http://confluence.octaviangameart.ru/pages/viewpage.action?pageId=360469",
    configuration = [FeignConfig::class]
)
interface DzenClient {
    @GetMapping("/")
    fun getHomePage(): String
}