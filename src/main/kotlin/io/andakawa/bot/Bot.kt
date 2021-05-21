package io.andakawa.bot

import io.andakawa.bot.commands.utils.*
import io.andakawa.bot.listeners.EventListener
import io.andakawa.bot.persistence.Store
import io.andakawa.bot.mal.GetAnimeList
import io.andakawa.bot.mal.PollMalApi
import net.dv8tion.jda.api.JDABuilder

class Bot(val token: String) {
    val commands = listOf (
        Ping(),
        GetAnime(),
        SearchAnime(),
        SearchAiringAnime(),
        SetPing(),
        GetAnimePage(),
        GetAnimeTrailer(),
        Wut()
    )

    fun start() {
        println("Commands available: ${commands.size}\n")
        val builder = JDABuilder.createDefault(Settings.BOT_TOKEN)
            .setAutoReconnect(true)
                .addEventListeners(EventListener(this, Store.create(Settings.STORE_LOCATION)))

        val jda = builder.build()
    }
}
