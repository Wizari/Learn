package com.gmail.wizaripost.learning.claude.lesson002.q3

class ProductImpl(
    private val name: String,
    private val price: Double,
) : Discountable {
    override fun originalPrice(): Double = price
}
