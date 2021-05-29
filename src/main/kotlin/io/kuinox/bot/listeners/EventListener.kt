package io.kuinox.bot.listeners

import io.kuinox.bot.Bot
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import kotlinx.coroutines.*

class EventListener(val bot: Bot, private val store: Store) : ListenerAdapter() {
    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        // Prevents message recognition if author is a bot
        if (event.author.isBot) return

        // Console output
        println("${event.message.guild.name} : [${event.message.channel.name}] ${event.author.name}: ${event.message.contentRaw}")
        runBlocking {
            launch {
                for (command in bot.commands) {
                    if (command.handle(event, store, bot)) {
                        break
                    }
                }
            }
        }


    }
}