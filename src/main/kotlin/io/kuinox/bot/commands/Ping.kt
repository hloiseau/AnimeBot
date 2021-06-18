package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Ping : CommandWithArguments("ping", PingHandler()) {
    override val helpDescription: String = "> ${Settings.PREFIX}ping → :ping_pong:"
}

class PingHandler : CommandHandler(false){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        event.message.reply(store.getContent().pingResponse).queue()
    }
}