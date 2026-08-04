package com.gmail.wizaripost.learning.claude.lesson002.q1

abstract class Employee(
    val name: String,
    val baseSalary: Double,
) {
    abstract fun calculateSalary(): Double

    fun printPaycheck() {
        println("Name: $name salary: ${calculateSalary()}")
    }
}
