package io.kuinox.bot.commands

import io.kuinox.bot.Bot
import io.kuinox.bot.Settings
import io.kuinox.bot.commands.helpers.CommandHandler
import io.kuinox.bot.commands.helpers.CommandWithArguments
import io.kuinox.bot.mal.GetAnimeList
import io.kuinox.bot.persistence.Store
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class GetAnime : CommandWithArguments("getAnime", GetAnimeHandler()) {

    override val helpDescription: String = "> ${Settings.PREFIX}getAnime <id> → Show anime with <id>"
}

class GetAnimeHandler : CommandHandler(true, "Search string.") {
    override suspend fun run(args: List<String>, event: GuildMessageReceivedEvent, store: Store, bot: Bot) {
        val animeList = GetAnimeList()
        val id = args.single().filter{ it.isDigit() }.toInt()
        val anime = animeList.getAnimeById(id)
        val message = StringBuilder()
        message.append("${anime?.titleEnglish!!} \n")
        message.append("${anime.imageUrl!!} \n")
        event.message.reply(message).queue()
    }
}