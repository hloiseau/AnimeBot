package io.kuinox.bot.commands.helpers

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(private val label: String) {

    open suspend fun handle(event: GuildMessageReceivedEvent, store: Store, bot: Bot): Boolean {
        val message: List<String> by lazy { event.message.contentRaw.split(" ") }
        val command = message[0]
        if (!command.startsWith(Settings.PREFIX)) return false
        val commandTxt = command.drop(1)
        if (!label.equals(commandTxt, ignoreCase = true)) {
            val content = store.getContent()
            val realCommand = content.aliases[commandTxt]
            if (!realCommand.equals(label, ignoreCase = true)) return false
        }
        run(message.drop(1), event, store, bot)
        return true
    }

    abstract suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot)
    abstract val helpDescription: String
}