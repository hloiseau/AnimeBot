package io.andakawa.bot.commands

import io.andakawa.bot.Bot
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(protected val label: String ) {

    open suspend fun handle(event: GuildMessageReceivedEvent, store: Store, bot: Bot) : Boolean {
        val message : List<String> by lazy { event.message.contentRaw.split(" ") }

        return if (message[0] == label) {
            run(event, store, bot)
            true
        } else {
            false
        }
    }

    abstract suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot)
    abstract val helpDescription: String
}