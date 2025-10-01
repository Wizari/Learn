package com.gmail.wizaripost.learning.feignClientTest

import feign.Logger
import feign.Request
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
    @Bean
    fun options(): Request.Options =
        Request.Options(10_000, 30_000) // connect/read timeout

    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL
}