package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.mal.GetAnimeList
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnimeTrailer : CommandWithArguments("trailer", GetAnimeTrailerHandler()) {
    override val helpDescription: String = "> ${Settings.PREFIX}trailer <Name> → Show trailer of anime with <Name>"
}

class GetAnimeTrailerHandler : CommandHandler(true, "Search string."){
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = args.single()
        val anime = animeList.searchForAnime(search)?.first()?.toFullAnime()
        val message = StringBuilder()
        message.append("${anime?.titleEnglish!!} \n")
        val trailer = Regex("^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$").find(anime.trailerUrl!!)?.groups?.get(1)?.value
        message.append("https://www.youtube.com/watch?v=${trailer} \n")
        event.channel.sendMessage(message).queue()
    }
}