package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class RemoveAlias : CommandWithArguments("removeAlias", RemoveAliasHandler()) {
    override val helpDescription: String
        get() = "> ${Settings.PREFIX}removeAlias aled\nRemove the alias 'aled'."

}

class RemoveAliasHandler : CommandHandler(false, "Alias to remove."){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val content = store.getContent()
        val alias = content.aliases[args[0]]
        if (alias == null) {
            event.message.reply("There is no such alias.").queue()
        }

        store.updateContent(
            content.copy(
                aliases = content.aliases - args[0]
            )
        )
        event.message.reply("The alias has been removed.").queue()
    }
}