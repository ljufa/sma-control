package com.github.ljufa.sma.control

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

/**
 * DB classes
 */
@Table("twitter.user_hashtag")
data class HashTag(val tag: String)
data class TwUser(val id: String)

@Table("sma.user")

data class User(
    @Id
    val id: Long? = null,
    val authId: String,
    val createdDate: Instant? = null
)

