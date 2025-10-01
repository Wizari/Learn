package com.gmail.wizaripost.learning.kafkaTest.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class HelloWorldMessage(
    val id: Long,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)