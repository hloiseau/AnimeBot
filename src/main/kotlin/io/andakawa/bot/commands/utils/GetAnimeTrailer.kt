package io.andakawa.bot.commands.utils

import io.andakawa.bot.Bot
import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.commands.CommandHandler
import io.andakawa.bot.commands.CommandWithArguments
import io.andakawa.bot.mal.GetAnimeList
import io.andakawa.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnimeTrailer : CommandWithArguments("animeTrailer", arrayOf(GetAnimeTrailerHandler())) {
    override val helpDescription: String = "> ${Settings.PREFIX}animeTrailer <id> → Show trailer of anime with <id>"
}

class GetAnimeTrailerHandler : CommandHandler(true, arrayOf("Search string.")){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = args.single()
        val anime = animeList.searchForAnime(search)?.first()?.ToFullAnime()
        val message = StringBuilder()
        message.append("${anime?.titleEnglish!!} \n")
        val trailer = Regex("^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$").find(anime.trailerUrl!!)?.groups?.get(1)?.value
        message.append("http://www.youtube.com/watch?v=${trailer} \n")
        event.channel.sendMessage(message).queue()
    }
}