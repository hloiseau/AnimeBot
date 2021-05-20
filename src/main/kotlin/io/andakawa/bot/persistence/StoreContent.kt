package io.andakawa.bot.persistence

import kotlinx.serialization.Serializable

@Serializable
data class StoreContent(val pingResponse: String)