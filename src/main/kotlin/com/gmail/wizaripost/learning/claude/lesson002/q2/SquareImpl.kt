package com.gmail.wizaripost.learning.claude.lesson002.q2

class SquareImpl(
    private val side: Int,
) : Drawable {
    override fun draw(): String = "Квадрат со стороной в $side"
}
