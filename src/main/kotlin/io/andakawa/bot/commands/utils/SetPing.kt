package io.andakawa.bot.commands.utils

import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class SetPing : Command("${Settings.PREFIX}setping") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store) {
        val newPing = event.message.contentRaw.removePrefix(label).trim();
        val newContent = store.getContent().copy(
            pingResponse = newPing
        )
        store.updateContent(newContent)
        event.channel.sendMessage("Ping response set to: $newPing").queue()
    }
}