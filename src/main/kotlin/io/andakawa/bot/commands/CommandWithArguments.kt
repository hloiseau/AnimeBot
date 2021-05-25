package io.andakawa.bot.commands

import io.andakawa.bot.Bot
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class CommandWithArguments(label: String, private val handlers: Array<CommandHandler>) : Command(label) {

    final override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        var msgArgs = event.message.contentRaw.split(' ')
        val compatibleHandler =
            handlers.firstOrNull { s -> s.argNames.size == msgArgs.size || (s.argNames.size < msgArgs.size && s.greedy) }

        if(compatibleHandler == null) {
            event.channel.sendMessage("Received ${msgArgs.size} arguments. Expected ${handlers.map { 
                s -> if(s.greedy) s.argNames.size.toString()+"+" else s.argNames.size.toString()
            }.joinToString(" or ")
            }.").queue()
            return
        }
        msgArgs = msgArgs.drop(1) //Skip command name.
        val handlerArgs = msgArgs.take(compatibleHandler.argNames.size - 1) + msgArgs.drop(compatibleHandler.argNames.size - 1).joinToString(" ")
        compatibleHandler.run(handlerArgs, event, store, bot )
    }
}