package com.gmail.wizaripost.learning.claude.lesson001

class Student(
    private val name: String,
    private val grades: MutableList<Int> = mutableListOf(),
) {
    fun addGrade(grade: Int) {
        grades.add(grade)
    }

    fun averageGrade(): Double {
        if (grades.isEmpty()) return 0.0
        return grades.average()
    }
}
