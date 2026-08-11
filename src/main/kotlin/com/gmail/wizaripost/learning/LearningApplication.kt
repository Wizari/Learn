package com.gmail.wizaripost.learning

import com.gmail.wizaripost.learning.claude.lesson003.q1.Calculate
import com.gmail.wizaripost.learning.claude.lesson003.q1.Point
import com.gmail.wizaripost.learning.feignClientTest.DzenService
import com.gmail.wizaripost.learning.feignClientTest.JackpotsNormalGroupApiClient
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.kafka.annotation.EnableKafka

// @EnableKafka
@SpringBootApplication
@EnableFeignClients
class LearningApplication(
    private val dzenService: DzenService,
    private val jackpotsNormalGroupApiClient: JackpotsNormalGroupApiClient,
// 	private val jackpotQueryService: JackpotQueryService
    private val calculate: Calculate,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
// 		println("=== *** ===")
// 		val html = dzenService.fetchPage()
// 		val rng = dzenService.getRng()
// 		println("=== RESPONSE START ===")
// 		println(html.take(500)) // выведем первые 500 символов
// 		println("=== RESPONSE END ===")
// 		println("[SEED] "+ rng.toString())
// 		val pageable: Pageable = PageRequest.of(0, 10)
// 		val jackpot = jackpotsNormalGroupApiClient.getAllForGroups(pageable)
// 		println(jackpot)

        val pointX = Point(10, 10)
        val pointY = Point(1, 1)
        if (pointY == pointX) {
            println("123123")
        }
        val pointCopy = pointX.copy(y = 1)
        println(pointCopy)

        val distance = calculate.distance(pointX, pointY)
        println(distance)
    }
}

fun main(args: Array<String>) {
    runApplication<LearningApplication>(*args)
}
