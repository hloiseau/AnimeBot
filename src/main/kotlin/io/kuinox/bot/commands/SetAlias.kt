package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class SetAlias : CommandWithArguments("setAlias", SetAliasHandler()) {
    override val helpDescription: String
        get() = "> ${Settings.PREFIX}setAlias aled help\nSet an alias 'aled' for the command 'help'"
}

class SetAliasHandler : CommandHandler(false, "Alias name.") {
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val content = store.getContent()
        val alias = content.aliases[args[0]]
        if (alias != null) {
            event.channel.sendMessage("This alias is already bind to $alias").queue()
        }

        store.updateContent(
            content.copy(
                aliases = content.aliases + Pair(args[0], args[1])
            )
        )
        event.channel.sendMessage("The alias has been added.").queue()
    }
}