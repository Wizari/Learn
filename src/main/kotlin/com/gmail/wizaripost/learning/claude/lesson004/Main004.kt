package com.gmail.wizaripost.learning.claude.lesson004

import com.gmail.wizaripost.learning.claude.lesson004.q123.Utils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Main004(
    private val check: Utils,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("*** 1 ***")

        val arr1 = listOf(10, 1, 2, 3, 4)
        println(check.hasDuplicates(arr1))

        println("*** 2 ***")

        val arr2 = listOf(10, 10, 1, 2, 3, 4)
        println(check.hasDuplicates(arr2))
        val arr3 = listOf("10", "10", "aaa", "aaa", "www", "ddd")
        val result = check.wordFrequency(arr3)
        println(result)

        println("*** 3 ***")

        val arr4 = mapOf(("a" to 1), ("b" to 5), ("c" to 5), ("d" to 5), ("d1" to 3), ("d2" to 4))
        val arr5 = mapOf(("a" to 1), ("b" to 5), ("c" to 5), ("d" to 5), ("e" to 5))

        val result2 = check.mergeCounts(arr4, arr5)
        println(result2)
    }
}

fun main(args: Array<String>) {
    runApplication<Main004>(*args)
}
