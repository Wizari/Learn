package com.gmail.wizaripost.learning.kafkaTest.controller

import com.gmail.wizaripost.learning.kafkaTest.dto.Bet
import com.gmail.wizaripost.learning.kafkaTest.dto.HelloWorldMessage
import com.gmail.wizaripost.learning.kafkaTest.kafka.HelloWorldProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(private val helloWorldProducer: HelloWorldProducer) {

    @PostMapping("/send")
    fun sendMessage(@RequestBody request: HelloWorldMessage): String {
        helloWorldProducer.sendMessage(request)
        return "Сообщение отправлено: ${request.text}"
    }


    @PostMapping("/bet")
    fun sendBet(@RequestBody request: Bet): String {
        helloWorldProducer.sendBet(request)
        return "Сообщение отправлено: ${request.totalBet}"
    }
}