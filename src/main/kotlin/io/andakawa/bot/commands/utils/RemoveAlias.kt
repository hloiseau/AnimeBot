package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.commands.CommandHandler
import io.andakawa.bot.commands.CommandWithArguments
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class RemoveAlias : CommandWithArguments("removeAlias", arrayOf(RemoveAliasHandler())) {
    override val helpDescription: String
        get() = "> ${Settings.PREFIX}removeAlias aled\nRemove the alias 'aled'."

}

class RemoveAliasHandler : CommandHandler(false, arrayOf("Alias to remove.")){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val content = store.getContent()
        val alias = content.aliases[args[0]]
        if (alias == null) {
            event.channel.sendMessage("There is no such alias.").queue()
        }

        store.updateContent(
            content.copy(
                aliases = content.aliases - args[0]
            )
        )
        event.channel.sendMessage("The alias has been removed.").queue()
    }
}