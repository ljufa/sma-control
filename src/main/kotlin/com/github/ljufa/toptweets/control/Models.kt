package com.github.ljufa.toptweets.control

import org.springframework.data.relational.core.mapping.Table

/**
 * DB classes
 */
@Table("twitter.user_hashtag")
data class HashTag(val tag: String)
data class TwUser(val id: String)
data class User(val id: String, val username: String, val password: String, val active: Boolean)


/**
 * DTO classes
 */
data class Login(val username: String, val password: String)
