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
    val createdDate: Instant? = null,
    val enabled: Boolean = true,
    val accounts: List<Account>? = null
)

enum class ExternalAccountType {
    TWITTER, FACEBOOK, INSTAGRAM,
    REDDIT, TIKTOK, LINKEDIN, GITHUB
}

@Table("sma.account")
data class Account(
    @Id
    val id: Long? = null,
    val user: User,
    val externalAccountId: String,
    val externalAccountType: ExternalAccountType,
    val createdDate: Instant? = null,
    val enabled: Boolean = true
)


/**
 * DTO classes
 */
data class TwitterPrefs(
    val hashtags: List<String>,
    val authors: List<String>,
    val places: List<String>,
    val rules: List<String>
)

data class UserPreferences(val twitterPrefs: TwitterPrefs)
