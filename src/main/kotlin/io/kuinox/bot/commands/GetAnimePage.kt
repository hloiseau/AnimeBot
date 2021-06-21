package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.mal.GetAnimeList
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnimePage : CommandWithArguments("page", GetAnimePageHandler()) {
    override val helpDescription: String = "> ${Settings.PREFIX}page <Name> → Show page of anime with <Name>"
}

class GetAnimePageHandler : CommandHandler(true, "Search string.") {
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = args.single()
        val anime = animeList.searchForAnime(search)?.first()?.toFullAnime()
        val message = StringBuilder()
        message.append("${anime?.titleEnglish!!} \n")
        message.append("${anime.url!!} \n")
        event.message.reply(message).queue()
    }
}