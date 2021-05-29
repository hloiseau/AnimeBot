package io.kuinox.bot.persistence

import kotlinx.serialization.Serializable

@Serializable
data class StoreContent(val pingResponse: String, val aliases: Map<String, String>)