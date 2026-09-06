package com.gmail.wizaripost.learning.claude.lesson003.q1

import org.springframework.stereotype.Service
import kotlin.math.sqrt

@Service
class Calculate {
    fun distance(
        p1: Point,
        p2: Point,
    ): Double {
        var x = 0.0
        var y = 0.0
        x =
            if (p1.x > p2.x) {
                (p1.x - p2.x).toDouble()
            } else {
                (p2.x - p1.x).toDouble()
            }

        y =
            if (p1.y > p2.y) {
                (p1.y - p2.y).toDouble()
            } else {
                (p2.y - p1.y).toDouble()
            }
        val sumSq = x * x + y * y
        return sqrt(sumSq)
    }
}
