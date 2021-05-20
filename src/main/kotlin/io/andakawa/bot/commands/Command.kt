package io.andakawa.bot.commands

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(private val label: String) {
    open fun handle(event: GuildMessageReceivedEvent) : Boolean {
        val message = event.message.contentRaw

        return if (message.startsWith(label)) {
            run(event)
            true
        } else {
            false
        }
    }

    abstract fun run(event: GuildMessageReceivedEvent)

}