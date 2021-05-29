package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.mal.GetAnimeList
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class SearchAnime : CommandWithArguments("anime", SearchAnimeHandler()) {

    override val helpDescription: String = "> ${Settings.PREFIX}anime <name> → Show anime containing <name>"
}

class SearchAnimeHandler: CommandHandler(true, "Search string.") {
    override suspend fun run(args:List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val search = args.single()
        val message = StringBuilder()
        for ( anime in animeList.searchForAnime(search)!!){
            message.append(anime.toFullAnime().titleEnglish).append("\n")
            message.append("${anime.imageUrl!!} \n")
        }
        event.channel.sendMessage(message).queue()
    }
}