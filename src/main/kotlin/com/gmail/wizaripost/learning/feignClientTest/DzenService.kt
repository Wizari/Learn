package com.gmail.wizaripost.learning.feignClientTest

import org.springframework.stereotype.Service

@Service
class DzenService(
    private val dzenClient: DzenClient,
    private val rng: RNGClient
) {
    fun fetchPage(): String{
        val rng: String = rng.getSeed()+
        println(rng)
       return dzenClient.getHomePage()
    }

    fun getRng(): String{
        return rng.getSeed()
    }

}