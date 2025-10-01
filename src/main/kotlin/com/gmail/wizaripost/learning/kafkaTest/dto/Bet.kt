package com.gmail.wizaripost.learning.kafkaTest.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Bet(
    val totalBet: Long,
    val groupId: Long
)