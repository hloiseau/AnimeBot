package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.mal.GetAnimeList
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnime : Command("getAnime") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val id = event.message.contentRaw.filter{ it.isDigit() }.toInt()
        val anime = animeList.getAnimeById(id)
        val message = StringBuilder();
        message.append("${anime?.titleEnglish!!} \n")
        message.append("${anime?.imageUrl!!} \n")
        event.channel.sendMessage(message).queue()
    }
    override val helpDescription: String = "> ${Settings.PREFIX}getAnime <id> → Show anime with <id>"
}