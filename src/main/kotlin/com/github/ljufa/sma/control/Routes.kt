package com.github.ljufa.sma.control

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
class RoutesConfig(val handler: HashTagHandler,
                   val userHandler: UserHandler
) {

    @Bean
    fun routes() = coRouter {
        "api".nest {
            POST("register", userHandler::registerUser)
            GET("hashtags", handler::handle)
        }
    }

}