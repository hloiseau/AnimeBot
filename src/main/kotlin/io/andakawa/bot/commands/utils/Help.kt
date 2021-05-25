package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.commands.CommandHandler
import io.andakawa.bot.commands.CommandWithArguments
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class Help : CommandWithArguments("help", arrayOf(HelpHandler())) {


    override val helpDescription: String = "> ${Settings.PREFIX}wut → Show help"
}

class HelpHandler : CommandHandler(false, arrayOf()) {
    override suspend fun run( args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val message = StringBuilder()
        message.append("Available commands : \n")
        for (command in bot.commands){
            message.append("${command.helpDescription} \n")
        }
        event.channel.sendMessage(message).queue()
    }
}