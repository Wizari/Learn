package com.gmail.wizaripost.learning.claude.lesson001

class Wallet(
    private var balance: Int,
) {
    fun deposit(amount: Int) {
        if (amount <= 0) throw IllegalArgumentException("Amount must be greater than zero")
        balance += amount
    }

    fun withdraw(amount: Int) {
        if (balance < amount) throw IllegalArgumentException("Недостаточно средств на счету!")
        balance -= amount
    }
}
