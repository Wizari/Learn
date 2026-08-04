package com.gmail.wizaripost.learning.claude.lesson002.q2

class CircleImpl(
    private val radius: Int,
) : Drawable {
    override fun draw(): String = "Круг радиусом $radius"
}
