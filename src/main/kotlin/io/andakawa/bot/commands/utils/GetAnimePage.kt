package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.mal.GetAnimeList
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnimePage : Command("animePage") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = event.message.contentRaw.ToSearch(label)
        val anime = animeList.searchForAnime(search)?.first()?.ToFullAnime()
        val message = StringBuilder();
        message.append("${anime?.titleEnglish!!} \n")
        message.append("${anime.url!!} \n")
        event.channel.sendMessage(message).queue()
    }

    override val helpDescription: String = "> ${Settings.PREFIX}animePage <id> → Show page of anime with <id>"
}