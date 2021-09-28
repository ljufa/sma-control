package com.github.ljufa.sma.control

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest
@AutoConfigureWebTestClient
@Testcontainers
class ApiEndToEndTest(@Autowired val client: WebTestClient, @Autowired val userRepository: UserRepository) {

    companion object {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:latest")

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url") { "r2dbc:postgresql://localhost:${postgreSQLContainer.getMappedPort(5432)}/${postgreSQLContainer.databaseName}" }
            registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername)
            registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword)
            registry.add("spring.flyway.url", postgreSQLContainer::getJdbcUrl)
            registry.add("spring.flyway.username", postgreSQLContainer::getUsername)
            registry.add("spring.flyway.password", postgreSQLContainer::getPassword)
        }
    }

    @BeforeEach
    fun cleanDB() = runBlocking {
        userRepository.deleteAll()
    }


    @Test
    fun `register respond 202 for non existing user`() {
        requestBodySpec().exchange().expectStatus().isAccepted
    }

    @Test
    fun `register respond 200 for existing user`() {
        requestBodySpec()
            .exchange().expectStatus().isAccepted
        requestBodySpec()
            .exchange().expectStatus().isOk
    }

    @Test
    fun `register respond 403 for anonymous user`() {
        client.post().uri("/api/register").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isForbidden
    }


    private fun requestBodySpec() = client.mutateWith(mockJwt())
        .mutateWith(csrf())
        .post().uri("/api/register").accept(MediaType.APPLICATION_JSON)


}