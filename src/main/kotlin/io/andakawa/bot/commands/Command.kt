package io.andakawa.bot.commands

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(protected val label: String) {

    open suspend fun handle(event: GuildMessageReceivedEvent, store: Store, bot: Bot): Boolean {
        val message: List<String> = event.message.contentRaw.split(" ")
        val command = message[0]
        if (!command.startsWith(Settings.PREFIX)) return false
        val commandTxt = command.drop(1)
        if (label != commandTxt) {
            val content = store.getContent()
            val realCommand = content.aliases[commandTxt]
            if( realCommand != label ) return false
        }
        run(event, store, bot)
        return true
    }

    abstract suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot)
    abstract val helpDescription: String
}