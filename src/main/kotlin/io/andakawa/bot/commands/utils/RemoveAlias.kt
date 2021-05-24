package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class RemoveAlias : Command("removeAlias") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val content = store.getContent()
        val words = event.message.contentRaw.split(" ");
        if (words.size != 2) {
            event.channel.sendMessage("This command should contain one and only one parameter. The alias should not contain any whitespace.").queue()
        }
        val alias = content.aliases[words[1]]
        if (alias == null) {
            event.channel.sendMessage("There is no such alias.").queue()
        }

        store.updateContent(
            content.copy(
                aliases = content.aliases - words[1]
            )
        )
        event.channel.sendMessage("The alias has been removed.").queue()
    }

    override val helpDescription: String
        get() = "> ${Settings.PREFIX}removeAlias aled\nRemove the alias 'aled'."

}