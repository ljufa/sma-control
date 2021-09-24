package com.github.ljufa.toptweets.control

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
class RoutesConfig(val handler: TopTweetsHandler,
                   val userHandler: UserLoginHandler) {

    @Bean
    fun routes() = coRouter {
        POST("/_login", userHandler::handle)
        "user-api".nest {
            GET("hashtags", handler::allHashTags)
        }
    }

}