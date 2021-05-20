package io.andakawa.bot.commands

import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(val label: String, val store: Store) {
    open fun handle(event: GuildMessageReceivedEvent) : Boolean {
        val message = event.message.contentRaw

        if (message.startsWith(label)) {
            run(event, store)
            return true
        } else {
            return false
        }
    }

    abstract fun run(event: GuildMessageReceivedEvent, store: Store)

}