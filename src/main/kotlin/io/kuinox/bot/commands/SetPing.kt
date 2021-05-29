package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class SetPing : CommandWithArguments("setping", SetPingHandler()) {

    override val helpDescription: String = "> ${Settings.PREFIX}setping <mention> → Set ping command to mention user or group"
}

class SetPingHandler : CommandHandler( true, "New ping message"){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val newPing = args.single()
        val newContent = store.getContent().copy(
            pingResponse = newPing
        )
        store.updateContent(newContent)
        event.channel.sendMessage("Ping response set to: $newPing").queue()
    }
}