package io.kuinox.bot.commands.helpers

import io.kuinox.bot.Bot
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class CommandHandler( val greedy: Boolean, vararg val argNames: String ) {
    abstract suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot)
}