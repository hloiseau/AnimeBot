package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.commands.CommandHandler
import io.andakawa.bot.commands.CommandWithArguments
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Ping : CommandWithArguments("ping", arrayOf(PingHandler())) {
    override val helpDescription: String = "> ${Settings.PREFIX}ping → :ping_pong:"
}

class PingHandler : CommandHandler(false, arrayOf()){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        event.channel.sendMessage(store.getContent().pingResponse).queue()
    }
}