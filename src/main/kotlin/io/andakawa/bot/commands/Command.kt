package io.andakawa.bot.commands

import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(private val label: String ) {

    open suspend fun handle(event: GuildMessageReceivedEvent, store: Store) : Boolean {
        val message = event.message.contentRaw

        return if (message.startsWith(label)) {
            run(event, store)
            true
        } else {
            false
        }
    }

    abstract suspend fun run(event: GuildMessageReceivedEvent, store: Store)

}