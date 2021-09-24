package com.github.ljufa.toptweets.control

import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class TopTweetsHandler(private val hashTagRepository: HashTagRepository) {

    suspend fun allHashTags(request: ServerRequest): ServerResponse {

        return ok().bodyAndAwait(hashTagRepository.findAll().asFlow())
    }

}

@Component
class UserLoginHandler(private val userRepository: UserRepository) {

    suspend fun handle(request: ServerRequest): ServerResponse {
        val login = request.awaitBody<Login>()
        val user = userRepository.findByUsernameAndPasswordAndActiveIsTrue(login.username, login.password)
            ?: return ServerResponse.status(HttpStatus.FORBIDDEN).buildAndAwait()
        return ok().bodyValueAndAwait(user)

    }

}

