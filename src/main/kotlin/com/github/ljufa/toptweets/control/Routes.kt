package com.github.ljufa.toptweets.control

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
class RoutesConfig(val handler: TopTweetsHandler,
                   val userHandler: UserLoginHandler) {

    @Bean
    fun routes() = coRouter {
        "api".nest {
            POST("users", userHandler::handle)
            GET("hashtags", handler::handle)
        }
    }

}