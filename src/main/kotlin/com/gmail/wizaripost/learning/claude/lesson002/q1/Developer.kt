package com.gmail.wizaripost.learning.claude.lesson002.q1

class Developer(
    name: String,
    baseSalary: Double,
) : Employee(name, baseSalary) {
    override fun calculateSalary(): Double = baseSalary
}
