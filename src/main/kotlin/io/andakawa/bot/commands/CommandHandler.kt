package io.andakawa.bot.commands

import io.andakawa.bot.Bot
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class CommandHandler( val greedy: Boolean, vararg val argNames: Array<String> ) {
    abstract suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot)
}