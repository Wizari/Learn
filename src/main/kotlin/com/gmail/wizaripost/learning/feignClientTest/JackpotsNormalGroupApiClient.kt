package com.gmail.wizaripost.learning.feignClientTest

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping


/**
 * Feign client for managing normal jackpot groups.
 * Connects to the OSS jackpots progressives normal service.
 */
@FeignClient(
    name = "jackpotsControllerGroupAdapter",
    url = "http://localhost:20801/api/normal/progressives/jackpots/groups",
)
interface JackpotsNormalGroupApiClient{
    @GetMapping("")
    fun getAllForGroups(pageable: Pageable):  String


}