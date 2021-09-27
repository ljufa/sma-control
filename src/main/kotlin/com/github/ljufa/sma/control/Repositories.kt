package com.github.ljufa.sma.control

import kotlinx.coroutines.flow.Flow
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


@Configuration
class FlywayConfig(private val env: Environment) {
    @Bean(initMethod = "migrate")
    fun flyway(): Flyway? {
        return Flyway(
            Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(
                    env.getRequiredProperty("spring.flyway.url"),
                    env.getRequiredProperty("spring.flyway.user"),
                    env.getRequiredProperty("spring.flyway.password")
                )
        )
    }
}

interface HashTagRepository : CoroutineCrudRepository<HashTag, String>
interface UserRepository : CoroutineCrudRepository<User, String> {
    fun findByAuthId(authId: String): Flow<User>
}
