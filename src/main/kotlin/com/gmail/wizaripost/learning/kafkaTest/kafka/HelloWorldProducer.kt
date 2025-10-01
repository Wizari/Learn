package com.gmail.wizaripost.learning.kafkaTest.kafka

import com.gmail.wizaripost.learning.kafkaTest.dto.Bet
import com.gmail.wizaripost.learning.kafkaTest.dto.HelloWorldMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class HelloWorldProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun sendMessage(message: HelloWorldMessage) {
//        kafkaTemplate.send("hello-world-topic", message)
        kafkaTemplate.send("hello-world-topic", message)
    }
    fun sendBet(message: Bet) {
//        kafkaTemplate.send("hello-world-topic", message)
        kafkaTemplate.send("JACKPOT_INCREMENT", message)
    }
}