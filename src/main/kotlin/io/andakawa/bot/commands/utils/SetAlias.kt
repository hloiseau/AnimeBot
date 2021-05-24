package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class SetAlias : Command("setAlias") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val content = store.getContent()
        val words = event.message.contentRaw.split(" ");
        if (words.size != 3) {
            event.channel.sendMessage("This command should contain 2 and only 2 parameter. The alias should not contain any whitespace.").queue()
        }
        val alias = content.aliases[words[1]]
        if (alias != null) {
            event.channel.sendMessage("This alias is already bind to $alias").queue()
        }

        store.updateContent(
            content.copy(
                aliases = content.aliases + Pair(words[1], words[2])
            )
        )
        event.channel.sendMessage("The alias has been added.").queue()
    }

    override val helpDescription: String
        get() = "> ${Settings.PREFIX}setAlias aled help\nSet an alias 'aled' for the command 'help'"

}