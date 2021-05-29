package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class Help : CommandWithArguments("help", HelpHandler()) {


    override val helpDescription: String = "> ${Settings.PREFIX}wut → Show help"
}

class HelpHandler : CommandHandler(false ) {
    override suspend fun run( args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val message = StringBuilder()
        message.append("Available commands : \n")
        for (command in bot.commands){
            message.append("${command.helpDescription} \n")
        }
        event.channel.sendMessage(message).queue()
    }
}