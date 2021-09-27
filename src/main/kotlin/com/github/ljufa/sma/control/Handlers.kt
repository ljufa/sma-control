package com.github.ljufa.sma.control

import kotlinx.coroutines.flow.firstOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.accepted
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class HashTagHandler(private val hashTagRepository: HashTagRepository) {

    suspend fun handle(request: ServerRequest): ServerResponse {
        return ok().bodyAndAwait(hashTagRepository.findAll())
    }

}

@Component
class UserHandler(private val userRepository: UserRepository) {

    suspend fun registerUser(request: ServerRequest): ServerResponse {
        val principal = request.awaitPrincipal() ?: return ServerResponse.status(403).buildAndAwait()
        return if (userRepository.findByAuthId(principal.name).firstOrNull() != null) {
            ok().buildAndAwait()
        } else {
            userRepository.save(User(authId = principal.name))
            accepted().buildAndAwait()
        }
    }

}

