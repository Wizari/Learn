package com.gmail.wizaripost.learning.claude.lesson002.q3

interface Discountable {
    abstract fun originalPrice(): Double

    fun discountedPrice(): Double = originalPrice() * 0.9
}
