package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class Wut : Command("${Settings.PREFIX}wut") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val message = StringBuilder();
        message.append("Available commands : \n")
        for (command in bot.commands){
            message.append("${command.helpDescription} \n")
        }
        event.channel.sendMessage(message).queue()
    }

    override val helpDescription: String = "> ${Settings.PREFIX}wut → Show help"
}