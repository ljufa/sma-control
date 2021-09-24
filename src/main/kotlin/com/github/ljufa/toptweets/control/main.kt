package com.github.ljufa.toptweets.control

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
class SmaControlApplication

fun main(args: Array<String>) {
	runApplication<SmaControlApplication>(*args)
}

