package com.gmail.wizaripost.learning.claude.lesson001

class Rectangle(
    private val width: Int,
    private val height: Int,
) {
    fun area(): Int = width * height

    fun perimeter(): Int = 2 * (width + height)

    fun isSquare(): Boolean = width == height

}
