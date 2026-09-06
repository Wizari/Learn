package com.gmail.wizaripost.learning.claude.lesson003

import com.gmail.wizaripost.learning.claude.lesson003.q1.Calculate
import com.gmail.wizaripost.learning.claude.lesson003.q1.Point
import com.gmail.wizaripost.learning.claude.lesson003.q2.Failure
import com.gmail.wizaripost.learning.claude.lesson003.q2.Pending
import com.gmail.wizaripost.learning.claude.lesson003.q2.Result
import com.gmail.wizaripost.learning.claude.lesson003.q2.Success
import com.gmail.wizaripost.learning.claude.lesson003.q3.Address
import com.gmail.wizaripost.learning.claude.lesson003.q3.Person
import com.gmail.wizaripost.learning.claude.lesson003.q3.PersonService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Main003(
    private val calculate: Calculate,
    private val result: Result,
    private val personService: PersonService,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val pointX = Point(10, 10)
        val pointY = Point(1, 1)
        if (pointY == pointX) {
            println("123123")
        }
        val pointCopy = pointX.copy(y = 1)
        println(pointCopy)

        val distance = calculate.distance(pointX, pointY)
        println(distance)

        println("*** 2 ***")
        println(result.describe(Success("1")))
        println(result.describe(Failure("2")))
        println(result.describe(Pending))

        println("*** 3 ***")
        val person1 = Person("Ivan", Address("N", "1111"))
        val person2 = Person("Ivan2", Address(zipCode = "2222"))
        val person3 = Person("Ivan3")

        println(personService.getCityOrDefault(person1))
        println(personService.getCityOrDefault(person2))
        println(personService.getCityOrDefault(person3))
    }
}

fun main(args: Array<String>) {
    runApplication<Main003>(*args)
}
