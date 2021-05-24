package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class SetPing : Command("setping") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val newPing = event.message.contentRaw.ToSearch(label);
        val newContent = store.getContent().copy(
            pingResponse = newPing
        )
        store.updateContent(newContent)
        event.channel.sendMessage("Ping response set to: $newPing").queue()
    }
    override val helpDescription: String = "> ${Settings.PREFIX}setping <mention> → Set ping command to mention user or group"
}