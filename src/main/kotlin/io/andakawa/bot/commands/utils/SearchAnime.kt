package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.mal.GetAnimeList
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class SearchAnime : Command("${Settings.PREFIX}anime") {
    override suspend fun run(event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = event.message.contentRaw.ToSearch(label)
        val message = StringBuilder();
        for ( anime in animeList.searchForAnime(search)!!){
            message.append(anime.ToFullAnime().titleEnglish).append("\n")
            message.append("${anime.imageUrl!!} \n")
        }
        event.channel.sendMessage(message).queue()
    }
    override val helpDescription: String = "> ${Settings.PREFIX}anime <name> → Show anime containing <name>"
}

