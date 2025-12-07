package com.gmail.wizaripost.learning.balls

fun calculateReelAnimationFlagsStatic(matrix: Array<Array<Int>>): BooleanArray {
    val result = booleanArrayOf(true, true, true, false, false)

    var totalBalls = 0
    for (reel in 0 until 5) {
        for (row in matrix[reel].indices) {
            if (matrix[reel][row] == 1) {
                totalBalls++
            }
        }
    }
    if (totalBalls >= 6) {
        return booleanArrayOf(true, true, true, true, true)
    }


    totalBalls = 0
    for (reel in 0 until 3) {
        for (row in matrix[reel].indices) {
            if (matrix[reel][row] == 1) {
                totalBalls++
            }
        }
    }

    for (row in matrix[3].indices) {
        if (matrix[3][row] == 1) {
            totalBalls++
        }
    }
    if ((totalBalls + matrix[3].size) >= 6) {
        result[3] = true
    }
    return result
}