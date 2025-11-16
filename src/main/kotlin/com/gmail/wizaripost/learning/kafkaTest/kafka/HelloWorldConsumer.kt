package com.gmail.wizaripost.learning.kafkaTest.kafka

import com.gmail.wizaripost.learning.kafkaTest.dto.HelloWorldMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class HelloWorldConsumer {

    private val logger = LoggerFactory.getLogger(HelloWorldConsumer::class.java)

   //@KafkaListener(topics = ["hello-world-topic"], groupId = "hello-world-group")
    fun receiveMessage(@Payload message: HelloWorldMessage) {
        logger.info("Получено сообщение: $message")
    }
}