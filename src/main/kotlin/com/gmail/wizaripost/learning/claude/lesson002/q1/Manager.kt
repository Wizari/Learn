package com.gmail.wizaripost.learning.claude.lesson002.q1

class Manager(
    name: String,
    baseSalary: Double,
    private val bonus: Double,
) : Employee(name, baseSalary) {
    override fun calculateSalary(): Double = baseSalary + bonus
}
