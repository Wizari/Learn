package com.gmail.wizaripost.learning

import com.gmail.wizaripost.learning.feignClientTest.DzenService
import com.gmail.wizaripost.learning.feignClientTest.JackpotsNormalGroupApiClient
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableFeignClients
@EnableKafka
class LearningApplication(
	private val dzenService: DzenService,
	private val jackpotsNormalGroupApiClient: JackpotsNormalGroupApiClient,
//	private val jackpotQueryService: JackpotQueryService
) : CommandLineRunner
{

	override fun run(vararg args: String?) {
//		println("=== *** ===")
//		val html = dzenService.fetchPage()
//		val rng = dzenService.getRng()
//		println("=== RESPONSE START ===")
//		println(html.take(500)) // выведем первые 500 символов
//		println("=== RESPONSE END ===")
//		println("[SEED] "+ rng.toString())
//		val pageable: Pageable = PageRequest.of(0, 10)
//		val jackpot = jackpotsNormalGroupApiClient.getAllForGroups(pageable)
//		println(jackpot)
	}
}

fun main(args: Array<String>) {
	runApplication<LearningApplication>(*args)
}
