package com.gmail.wizaripost.learning.claude.lesson003.q2

import org.springframework.stereotype.Service

@Service
class Result {
    fun describe(result: PaymentResult): String =
        when (result) {
            is Success -> "Success: ${result.transactionId}"
            is Failure -> "Failure: ${result.reason}"
            Pending -> "Данные: Pending"
        }
}
