package io.andakawa.bot.commands.utils

import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Ping : Command("${Settings.PREFIX}ping") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store) {
        event.channel.sendMessage(store.getContent().pingResponse).queue()
    }
}