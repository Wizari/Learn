package com.gmail.wizaripost.learning.claude.lesson003.q1

import org.springframework.stereotype.Service
import kotlin.math.hypot

@Service
class Calculate {
    fun distance(
        p1: Point,
        p2: Point,
    ): Double {
        return hypot((p1.x - p2.x).toDouble(), (p1.y - p2.y).toDouble())
    }
}
