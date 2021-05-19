package io.andakawa.bot

import io.andakawa.bot.commands.utils.Ping
import io.andakawa.bot.listeners.eventListener
import net.dv8tion.jda.api.JDABuilder

class Bot(val token: String) {
    val commands = listOf (
            Ping()
    )

    fun start() {
        println("Commands available: ${commands.size}\n")
        val builder = JDABuilder.createDefault(Settings.BOT_TOKEN)
                .setAutoReconnect(true)
                .addEventListeners(eventListener(this))

        val jda = builder.build()
    }
}
