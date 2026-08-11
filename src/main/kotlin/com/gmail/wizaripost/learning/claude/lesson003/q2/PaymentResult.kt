package com.gmail.wizaripost.learning.claude.lesson003.q2

sealed class PaymentResult

class Success(
    val transactionId: String,
) : PaymentResult()

class Failure(
    val reason: String,
) : PaymentResult()

object Pending : PaymentResult()
