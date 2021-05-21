package io.andakawa.bot

import io.andakawa.bot.commands.utils.GetAnime
import io.andakawa.bot.commands.utils.Ping
import io.andakawa.bot.commands.utils.SearchAiringAnime
import io.andakawa.bot.listeners.EventListener
import io.andakawa.bot.commands.utils.SearchAnime
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.JDABuilder

class Bot(val token: String) {
    val commands = listOf (
        Ping(),
        GetAnime(),
        SearchAnime(),
        SearchAiringAnime()
    )

    fun start() {
        println("Commands available: ${commands.size}\n")
        val builder = JDABuilder.createDefault(Settings.BOT_TOKEN)
            .setAutoReconnect(true)
                .addEventListeners(EventListener(this, Store.create(Settings.STORE_LOCATION)))

        val jda = builder.build()
    }
}
